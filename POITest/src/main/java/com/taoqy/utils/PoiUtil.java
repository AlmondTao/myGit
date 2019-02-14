package com.taoqy.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
public class PoiUtil {

    public void exportExcel(String exportFilePath) throws IOException {
        File exportFile = new File(exportFilePath);

        if(exportFile.exists()){
            exportFile.createNewFile();
        }
        //导出的数据流
        FileOutputStream fileOutputStream = new FileOutputStream(exportFile);
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
        workbook.write(fileOutputStream);
//        fileOutputStream.flush();
//        fileOutputStream.close();

    }
}
