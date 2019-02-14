package com.taoqy.controller;

import com.itextpdf.text.DocumentException;
import com.taoqy.utils.ITextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/2/14
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
@RestController
@RequestMapping("/pdf")
public class ITextController {

    @Autowired
    private ITextUtil iTextUtil;

    @RequestMapping("/downLoad")
    public void downLoadPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=table.pdf");

        iTextUtil.exportTablePDF(response.getOutputStream());
    }

}
