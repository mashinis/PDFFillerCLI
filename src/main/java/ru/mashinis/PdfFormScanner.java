package ru.mashinis;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import ru.mashinis.model.Field;
import ru.mashinis.pdfgeneration.CyrillicPdfFormFiller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PdfFormScanner {
    public static void main(String[] args) {
//        CyrillicPdfFormFiller pdfFormFiller = new CyrillicPdfFormFiller();
//        List<Field> fields = pdfFormFiller.readFormAndSaveFields();
//        //pdfFormFiller.fillPdfForm();
//
//        System.out.println("INSERT INTO fields (name, alias)\r\n" +
//                "VALUES");
//        int count = 1;
//        for (Field field : fields) {
//            System.out.println("('" + 1 + "', '" + count++ + "'),");
//            //System.out.print("('" + field.getName() + "', '" + field.getAlias() + "'),\r\n");
//        }

    }
}

