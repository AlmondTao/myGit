package com.taoqy.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/2/13
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
@Component
public class ITextUtil {

    public void exportPDF(OutputStream outputStream) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, outputStream);
        document.addTitle("example for PDF");
        document.open();
        document.add(new Paragraph("Hello World"));

        document.close();
    }


    public void exportTablePDF(OutputStream outputStream) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);

//        File file = new File(filePath);
//        if(file.exists()){
//            file.createNewFile();
//        }
        PdfWriter instance = PdfWriter.getInstance(document,outputStream);
        //打成jar包后需要这样读取文件，否则找不到
        //simsun.ttc宋体
        String path = new ClassPathResource("/simfang.ttf").getPath();
        System.out.println(path);
        //simsun.ttc + ,1或者0，9
        BaseFont baseFont =  BaseFont.createFont(path,BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);

        //        document.addTitle("Table for PDF");

        document.open();
        //如果size过小，则文字信息显示不出来
        float size = 30f;
        int rowIndex = 1;
        PdfPTable pdfPTable = new PdfPTable(2);

        PdfPCell pdfPCell;
        for(;rowIndex < 21;rowIndex++){
            pdfPCell = new PdfPCell(new Phrase("第" + rowIndex + "行",font));
            pdfPCell.setFixedHeight(size);
            pdfPTable.addCell(pdfPCell);
            pdfPCell = new PdfPCell(new Phrase("第"+rowIndex+"行的值",font));
            pdfPCell.setFixedHeight(size);
            pdfPTable.addCell(pdfPCell);
//            Row row = sheet.createRow(rowIndex);
//            row.createCell(0).setCellValue("第"+rowIndex+"行");
//            row.createCell(1).setCellValue("第"+rowIndex+"行的值");
        }
        document.add(pdfPTable);
        document.close();

    }


}
