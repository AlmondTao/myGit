package com.taoqy;

import com.taoqy.utils.PoiUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class POITest {

    @Autowired
    private PoiUtil poiUtil;

    @Test
    public void exportExcel() throws IOException {
        poiUtil.exportExcel("E:\\poiTest\\test.xlsx");
        System.out.println("excel导出成功");
    }
}
