package com.taoqy.handler;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/30
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
public class MyException extends Exception{

    public MyException(String message) {
        super("这是自定义异常"+message);
    }
}
