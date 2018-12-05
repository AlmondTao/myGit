package com.taoqy.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DataDao {

    private static final HashMap<Integer,String> data = new HashMap<>();
    public DataDao(){
        data.put(1, "一");
        data.put(2, "二");
        data.put(3, "三");
        data.put(4, "四");
    }
    public String getData(Integer key){
        System.out.println("get data from dao");
        return data.get(key);
    }
}
