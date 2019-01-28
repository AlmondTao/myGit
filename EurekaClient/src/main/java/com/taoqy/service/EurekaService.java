package com.taoqy.service;

import com.taoqy.bean.Point;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/26
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
@Service
public class EurekaService {

    public String exception() throws SerialException {
        if(0==0){
            throw new SerialException();
        }
        return "exception服务层";
    }

    public void getTree(){
        List<Point> allPointList = new ArrayList<>();
        List<Point> resultTree = new ArrayList<>();
        for (Point point:allPointList){

        }
    }
}
