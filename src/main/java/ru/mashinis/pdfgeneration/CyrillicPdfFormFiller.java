package ru.mashinis.pdfgeneration;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import ru.mashinis.model.ContentFieldJson;
import ru.mashinis.model.Field;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Что делает класс:
 * 1. Заполняет PDF Форму
 * 2. Сканирует файл pdf, извлекает название и подсказку поля формы
 * Поддержка кириллицы полная!
 */
public class CyrillicPdfFormFiller {
    // Путь к исходному PDF файлу с формой
    private String uriInPdfForm;
    // Путь для сохранения заполненного PDF файла
    private final String URI_OUT_PDF_FORM = "outputForm.pdf";
    // Путь к файлу шрифта
    private final String URL_FONT = "fonts/LiberationSans-Regular.ttf";

    public CyrillicPdfFormFiller(String uriIn) {
        this.uriInPdfForm = uriIn;
    }


    // Заполняем форму PDF
    public void fillPdfForm(List<ContentFieldJson> contentFieldJsons) {

        // Чтение пути PDF файла с формой
        String filePdfPath = CyrillicPdfFormFiller.class.getClassLoader().getResource(uriInPdfForm).getPath();

        // Чтение пути шрифта
        String fileFontPath = CyrillicPdfFormFiller.class.getClassLoader().getResource(URL_FONT).getPath();

        // Чтение исходного PDF файла с формой
        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(filePdfPath), new PdfWriter(URI_OUT_PDF_FORM))) {

            PdfAcroForm acroForm = PdfAcroForm.getAcroForm(pdfDocument, true);

            for (ContentFieldJson content : contentFieldJsons) {
                // Получение текстового поля формы по его имени
                PdfFormField field = acroForm.getField(content.getFieldName());

                // Проверка, что поле найдено и является текстовым полем
                if (field instanceof PdfTextFormField) {
                    // Установка значения текстового поля с учетом кириллицы
                    PdfFont font = PdfFontFactory.createFont(fileFontPath, PdfEncodings.IDENTITY_H, true);

                    // Можно попробовать кириллицу в поля добавить таким способом
                     //field.getFieldName().toUnicodeString();
                    //field.setValue(fieldValue.getValue().toUnicodeString());

                    field.setFont(font).setValue(content.getValue());
                } else {
                    System.out.println("Поле для заполнения не найдено!");
                }
            }

            // Растрировать поля формы
            acroForm.flattenFields();

            // Закрываем документ PDF. Если этого не сделать, то
            // заполненная форма появится только когда закрыть приложение
            pdfDocument.close();

            System.out.println("PDF форма успешно заполнена и сохранена в: " + URI_OUT_PDF_FORM);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Сканируем PDF форму и находим имя поля и подсказку.
    // Имя поля уникальное на всю форму.
    // Подсказка будет использоваться для названия поля
    // Метод сделан с заделом на будущее
    // Метод реализован не совсем верно. Все таки нужно реализовать сканирование папки pdf на наличие файлов с формами,
    // если таких форм нет в БД, то уже после этого можно загружать форму в этот класс.
    public List<Field> readFormAndSaveFields(int formId) {
        List<Field> fieldList = new ArrayList<>();

        // Чтение пути PDF файла с формой
        String filePdfPath = CyrillicPdfFormFiller.class.getClassLoader().getResource(uriInPdfForm).getPath();

        // Чтение исходного PDF файла с формой
        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(filePdfPath))) {

            PdfAcroForm acroForm = PdfAcroForm.getAcroForm(pdfDocument, true);

            if (acroForm != null) {
                Map<String, PdfFormField> fields = acroForm.getFormFields();
                for (Map.Entry<String, PdfFormField> entry : fields.entrySet()) {

                    PdfFormField field = entry.getValue();

                    String fieldName = field.getFieldName().toUnicodeString();

                    // Проверка на null перед вызовом метода toUnicodeString()
                    String fieldHint = (field.getAlternativeName() != null) ? field.getAlternativeName().toUnicodeString() : "";

                    fieldList.add(new Field(formId, fieldName, fieldHint));
                }
            }
            return fieldList;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
