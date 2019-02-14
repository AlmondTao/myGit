package com.taoqy;

import com.itextpdf.text.DocumentException;
import com.taoqy.utils.ITextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ITextTest {
    @Autowired
    private ITextUtil iTextUtil;


    @Test
    public void exportPDF() throws IOException, DocumentException {
        File file = new File("E:\\poiTest\\test.pdf");
        if(file.exists()){
            file.createNewFile();
        }
        iTextUtil.exportPDF(new FileOutputStream(file));
        System.out.println("pdf导出成功");
    }

    @Test
    public void exportTablePDF() throws IOException, DocumentException {
        File file = new File("E:\\poiTest\\tableTest.pdf");
        if(file.exists()){
            file.createNewFile();
        }

        iTextUtil.exportTablePDF(new FileOutputStream(file));
        System.out.println("pdf表格导出成功");
    }
}
