package ru.mashinis.pdfgeneration;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import ru.mashinis.model.FieldValue;

import java.io.IOException;
import java.util.List;

/**
 * Заполняет PDF Форму
 * Поддержка кириллицы полная!
 */
public class CyrillicPdfFormFiller {
    // Путь к исходному PDF файлу с формой
    private final String URL_PDF_FORM = "pdf/explanatory_note.pdf";
    // Путь к файлу шрифта
    private final String URL_FONT = "fonts/LiberationSans-Regular.ttf";
    public void fillPdfForm(List<FieldValue> fieldValues) {

        // Путь для сохранения заполненного PDF файла
        String outputPdfPath = "outputForm.pdf";

        // Чтение пути PDF файла с формой
        String filePdfPath = CyrillicPdfFormFiller.class.getClassLoader().getResource(URL_PDF_FORM).getPath();

        // Чтение пути шрифта
        String fileFontPath = CyrillicPdfFormFiller.class.getClassLoader().getResource(URL_FONT).getPath();

        // Чтение исходного PDF файла с формой
        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(filePdfPath), new PdfWriter(outputPdfPath))) {

            PdfAcroForm acroForm = PdfAcroForm.getAcroForm(pdfDocument, true);

            for (FieldValue fieldValue : fieldValues) {
                // Получение текстового поля формы по его имени
                PdfFormField field = acroForm.getField(fieldValue.getFieldAlias());

                // Проверка, что поле найдено и является текстовым полем
                if (field instanceof PdfTextFormField) {
                    // Установка значения текстового поля с учетом кириллицы
                    PdfFont font = PdfFontFactory.createFont(fileFontPath, "Identity-H", true);
                    field.setFont(font).setValue(fieldValue.getValue());
                } else {
                    System.out.println("Поле для заполнения не найдено!");
                }

                ;
            }

            // Растрировать поля формы
            acroForm.flattenFields();

            System.out.println("PDF форма успешно заполнена и сохранена в " + outputPdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
