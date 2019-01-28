package com.taoqy.bean;

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
public class Point {

    private String id;
    private String pointName;
    private String parentId;
    private String url;
    private List<Point> childPoint;
    private Integer isOwn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
