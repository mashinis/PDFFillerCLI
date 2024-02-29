package ru.mashinis.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ru.mashinis.dao.implement.ContentDao;
import ru.mashinis.dao.implement.FieldDao;
import ru.mashinis.dao.implement.FormDao;
import ru.mashinis.dao.service.FormService;
import ru.mashinis.dbmanager.DatabaseManager;
import ru.mashinis.model.*;
import ru.mashinis.pdfgeneration.CyrillicPdfFormFiller;

import java.lang.reflect.Type;
import java.util.*;

public class MainController {
    private static Scanner scanner;
    private boolean isAuthenticated;
    private static AuthController authController;
    private User userAuth;
    private List<Form> forms;
    private List<Field> fields;
    private Form currentForm; // Форма, которую выбрал пользователь
    private Set<Integer> setFormId; // Хранит все актуальные form_id. Нужен для проверки, существует такой id или нет

    public MainController() {
        scanner = new Scanner(System.in);
        authController = new AuthController(scanner);
        this.isAuthenticated = false;
        this.userAuth = null;
        this.forms = null;
        this.fields = null;
        this.currentForm = null;
        FormDao formDao = new FormDao(DatabaseManager.getDataSource());
        this.setFormId = formDao.getUniqueIds();
    }

    public void start() {

        // Проходим авторизацию
        userAuth = authController.start(); // Закомментировал для тестирования

        // Меню заполнения формы
        while (true) {
            System.out.println("Меню заполнения формы.\nВыберите действие: \r\n" +
                    "\\forms - Список всех доступных форм;\r\n" +
                    "\\fields [id Form] - Список всех доступных полей выбранной формы;\r\n" +
                    "\\all [id Form] - Последовательное заполнение всех полей формы;\r\n" +
                    "\\pdf [PDF ID Form] - Заполнить PDF форму;\r\n" +
                    "\\content [ID] - Вывести ранее заполненную форму;\r\n" +
                    "\\upload - Загрузить новую форму;\r\n" +
                    "\\exit - Выход из приложения."
            );

            String input = scanner.nextLine();
            String[] s = input.toLowerCase().split(" ");
            String choice = s[0].trim();
            int i;

            switch (choice.toLowerCase()) {

                case "\\forms": // Список всех доступных форм
                    listForms();
                    continue;

                case "\\fields": // Вывод всех полей выбранной формы
                    i = stringToInt(s[1].trim());
                    getCurrentForm(i);

                    if (i == -1 || currentForm == null) {
                        System.err.println("Такой формы не существует!");
                    } else {
                        listFields(i);
                    }
                    continue;

                case "\\all": // Последовательное заполнение полей
                    i = stringToInt(s[1].trim());
                    getCurrentForm(i);

                    if (i == -1 || currentForm == null) {
                        System.err.println("Такой формы не существует!");
                    } else {
                        fillInForm(i);
                    }
                    continue;

                case "\\pdf": // Заполнить форму PDF данными из БД
                    i = stringToInt(s[1].trim());

                    if (i == -1) { // Здесь нет проверки, что такая форма существует
                        System.err.println("Такой формы не существует!");
                    } else {
                        fillPdfForm(i);
                    }
                    continue;

                case "\\content": // Вывести заполненные поля
                    i = stringToInt(s[1].trim());

                    if (i == -1) {
                        System.err.println("Такой формы не существует!");
                    } else {
                        contentForm(i);
                    }
                    continue;

                case "\\upload": // Загрузить новую форму
                    uploadNewForm();
                    continue;

                case "\\exit":
                    System.exit(0);

                default:
                    System.err.println("Неверная команда. Попробуйте еще раз.");
            }
        }

    }

    private void uploadNewForm() {
        FormDao formDao = new FormDao(DatabaseManager.getDataSource());
        FieldDao fieldDao = new FieldDao(DatabaseManager.getDataSource());
        setFormId = formDao.getUniqueIds();
        FormService formService = new FormService(formDao, fieldDao);

        // Пока не будет ни какой проверки названия формы,
        // Но на уровне БД поле с названием формы уникальное
        System.out.println("Введите имя формы.");
        String nameForm = scanner.nextLine();
        System.out.println("Введите имя файла.");
        String nameFile = scanner.nextLine();
        String pathFile = "pdf/" + nameFile;

        // Нужна проверка, существует такой файл или нет

        int formId = formService.createForm(new Form(nameForm, pathFile));
        System.out.println("Форма добавлена в систему. Ее ID: " + formId);
    }

    private void getCurrentForm(int id) {
        FormDao formDao = new FormDao(DatabaseManager.getDataSource());
        // Optional нужен для упрощенной обработки null
        Optional<Form> optionalForm = formDao.getById(id);
        // Проверяем, присутствует ли значение
        if (optionalForm.isPresent()) {
            // Объект присутствует, можем его получить
            currentForm = optionalForm.get();
        } else {
            currentForm = null;
        }
    }

    // вывод в консоль ранее заполненной формы
    private void contentForm(int id) {
        ContentDao contentDao = new ContentDao(DatabaseManager.getDataSource());
        // Optional нужен для упрощенной обработки null
        Optional<Content> optionalContent = contentDao.getById(id);
        // Проверяем, присутствует ли значение
        if (optionalContent.isPresent()) {
            // Объект присутствует, можем его получить
            Content content = optionalContent.get();
            String value = content.getContent();
            System.out.println(content.toString());

            Gson gson = new Gson();
            Type fieldType = new TypeToken<List<ContentFieldJson>>() {
            }.getType();
            List<ContentFieldJson> contentFields = gson.fromJson(value, fieldType);
            int count = 1;

            for (ContentFieldJson contentField : contentFields) {
                System.out.println(count++ + ". " + contentField.getFieldAlias() + ": " + contentField.getValue());
            }

        } else {
            System.err.println("Не могу найти такую форму!");
        }
    }

    // Список всех форм
    // Нужно сделать вывод форм, доступных только для конкретного пользователя
    private void listForms() {
        FormDao formDao = new FormDao(DatabaseManager.getDataSource());
        setFormId = formDao.getUniqueIds();
        forms = formDao.getAll();

        if (forms.size() == 0) {
            System.out.println("Нет сохраненных форм!");
            return;
        }

        System.out.println("Список доступных форм:");
        for (Form form : forms) {
            System.out.println(form.toString());
        }
    }

    // Вывод всех полей формы == id
    private void listFields(int idForm) {
        FieldDao fieldDao = new FieldDao(DatabaseManager.getDataSource());
        fields = fieldDao.getAll(idForm);

        if (fields.size() == 0) {
            System.out.println("В форме нет полей для заполнения или такой формы не существует.");
            return;
        }

        System.out.println("Список доступных полей:");
        for (Field field : fields) {
            System.out.println(field.toString());
        }
    }

    // Последовательное заполнение формы
    private void fillInForm(int idForm) {
        FieldDao fieldDao = new FieldDao(DatabaseManager.getDataSource());

        String input;
        // После загрузки новой формы она почему то не правильно выбирается при заполнении
        if (fields == null || fields.size() == 0) {
            fields = fieldDao.getAll(idForm);
        }

        List<ContentFieldJson> contentList = new ArrayList<>();
        int count = 1;
        System.out.println("Форма: " + currentForm.getName());

        for (Field field : fields) {
            System.out.println(count++ + ". " + field.getFieldAlias());
            // Пока без валидации введенных данных
            input = scanner.nextLine();

            int fieldId = field.getFieldId();
            String fieldName = field.getFieldName();
            String fieldAlias = field.getFieldAlias();

            contentList.add(new ContentFieldJson(fieldId, fieldName, fieldAlias, input));
        }

        System.out.println("У вас есть шанс исправить ошибки.\nПроверить заполнение полей? (Да/Нет)");
        // Здесь будем исправлять ошибки
        input = scanner.nextLine();

        if (input.toLowerCase().startsWith("да")) {
            count = 1;
            for (ContentFieldJson contentFieldJson : contentList) {
                System.out.println(count++ + ". " + contentFieldJson.getFieldAlias() + ": " + contentFieldJson.getValue());
            }
            System.out.println("Чтоб исправить значение поля, введите: [ID Поля] и нажмите Enter.\n" +
                    "Список полей: \\l\n" +
                    "Выйти из редактора значений поля: \\q");

            // Цикл редактирования значений полей должен быть бесконечным,
            // пока пользователь не ввел '\q'
            while (true) {
                input = scanner.nextLine();

                if (input.toLowerCase().startsWith("\\q")) {
                    break;
                }

                if (input.toLowerCase().startsWith("\\l")) {
                    count = 1;
                    for (ContentFieldJson contentFieldJson : contentList) {
                        System.out.println(count++ + ". " + contentFieldJson.getFieldAlias() + ": " + contentFieldJson.getValue());
                    }
                    continue;
                }

                int i = stringToInt(input.toLowerCase());

                if (i < contentList.size() && i != -1) {
                    ContentFieldJson contentFieldJson = contentList.get(i - 1);
                    System.out.println("Старое значение: " + contentFieldJson.getValue());
                    System.out.print("Новое значение: ");
                    input = scanner.nextLine();

                    contentFieldJson.setValue(input);
                    contentList.set(i - 1, contentFieldJson);
                    System.out.println("Изменения сохранены.");
                } else {
                    System.err.println("Поле с таким индексом не существует!");
                }
            }
        }

        System.out.println("Сохраняем запись в БД...");
        // Здесь должно быть сохранение в БД
        Gson gson = new Gson();
        String json = gson.toJson(contentList);

        ContentDao contentDao = new ContentDao(DatabaseManager.getDataSource());
        int contentId = contentDao.create(new Content(idForm, userAuth.getId(), json));

        if (contentId != 0) {
            System.out.println("ID заполненной формы: " + contentId + ". Запомните его. Он может потребоваться.");
        } else {
            System.err.println("Ошибка при сохранении формы!");
        }

        System.out.println("Заполнить форму PDF из ранее введенных данных? (Да/Нет)");
        input = scanner.nextLine();

        if (input.toLowerCase().startsWith("да")) {
            CyrillicPdfFormFiller cyrillicPdfFormFiller = new CyrillicPdfFormFiller(currentForm.getFormPath());
            cyrillicPdfFormFiller.fillPdfForm(contentList);
        }
    }

    /**
     * Заполняю PDF форму из данных в БД, хранящихся под идентификатором: PDF ID Form
     */
    private void fillPdfForm(int pdfIdForm) {
        ContentDao contentDao = new ContentDao(DatabaseManager.getDataSource());
        Optional<Content> optionalContent = contentDao.getById(pdfIdForm);
        // Проверяем, присутствует ли значение
        if (optionalContent.isPresent()) {
            // Объект присутствует, можем его получить
            Content content = optionalContent.get();
            String value = content.getContent();
            int formId = content.getFormId();
            getCurrentForm(formId);

            System.out.println(content.toString());

            Gson gson = new Gson();
            Type fieldType = new TypeToken<List<ContentFieldJson>>() {}.getType();
            List<ContentFieldJson> contentList = gson.fromJson(value, fieldType);

            CyrillicPdfFormFiller cyrillicPdfFormFiller = new CyrillicPdfFormFiller(currentForm.getFormPath());
            cyrillicPdfFormFiller.fillPdfForm(contentList);

        } else {
            System.err.println("Ошибка: Объект отсутствует!");
        }
    }

    private int stringToInt(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i > 0) {
                return i;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
