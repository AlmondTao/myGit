package com.taoqy.controller;

import com.taoqy.guava.GuavaCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/guava")
public class GuavaCacheController {

    @Autowired
    private GuavaCache guavaCache;

    @RequestMapping("/getDataFromLoadingCache")
    public String getData(@RequestParam(value = "key",required = false) String key){
        String value = guavaCache.getDataFromLoadingCache(new Integer(key));
        return value;
    }

    @RequestMapping("/getDataFromCache")
    public String getDataFromCache(@RequestParam(value = "key",required = false)String key){
        String dataFromCache = guavaCache.getDataFromCache(new Integer(key));
        return dataFromCache;
    }
}
