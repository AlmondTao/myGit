package com.taoqy.bean;

//import com.taoqy.util.SerializedUtil;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2018/12/17
 * @see [相关类/方法]
 */
public class ResponseMessage implements Serializable {
    int statusCode;
    String description;
    Object dataObject;
    public ResponseMessage(HttpStatus statusCode, String description) {
        this.setMessage(statusCode, description, null);
    }

    public ResponseMessage(HttpStatus statusCode, String description, Object dataObject) {
        this.setMessage(statusCode, description, dataObject);
    }
    public void setMessage(HttpStatus statuCode, String description, Object dataObject) {
        this.statusCode = statuCode.value();
        this.description = description;
        if(dataObject != null) {
            this.dataObject = dataObject;
        } else {
            this.dataObject = new Object();
        }
    }


    public int getStatus() {
        return statusCode;
    }

    public void setStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }

//    @Override
//    public String toString() {
//        return SerializedUtil.parseJsonString(this);
//    }
}
