package com.taoqy.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.taoqy.dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class GuavaCache {


    @Autowired
    @Qualifier("loadingCache")
    private LoadingCache loadingCache;

    @Autowired
    @Qualifier("cache")
    private Cache cache;


    public String getDataFromLoadingCache(Integer key){
        String dataCache = "";

        try {
            System.out.println("get data from loadingCache");
            dataCache = (String)loadingCache.get(key);

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dataCache;
    }

    public String getDataFromCache(Integer key){
        String data = null;
        System.out.println("get data from cache");
        try {
            data = (String) cache.get(key, () -> {
                System.out.println("cache not have value");
                System.out.println("create value by callable");
                String s = UUID.randomUUID().toString();
                cache.put(key, s);
                return s;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return data;
    }


}
