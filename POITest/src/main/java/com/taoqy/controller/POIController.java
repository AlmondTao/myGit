package com.taoqy.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/2/13
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
@RestController
@RequestMapping("/excel")
public class POIController {

    @RequestMapping("/downLoad")
    public void downLoadExcel(HttpServletResponse response) throws IOException {

        Workbook workbook;
        //excel2007
        workbook = new XSSFWorkbook();

        //获得第一张表
        Sheet sheet = workbook.createSheet();
//        Sheet sheet = workbook.getSheetAt(0);

        int rowIndex = 1;
        for(;rowIndex < 21;rowIndex++){
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue("第"+rowIndex+"行");
            row.createCell(1).setCellValue("第"+rowIndex+"行的值");

        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        String filename = format+".xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename="+ filename);
        workbook.write(response.getOutputStream());
    }
}
