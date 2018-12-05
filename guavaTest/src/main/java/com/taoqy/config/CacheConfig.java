package com.taoqy.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.taoqy.dao.DataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean("loadingCache")
    public LoadingCache createLoadingCache(DataDao dataDao){
        LoadingCache build = CacheBuilder.newBuilder()
                //设置最大数量
                .maximumSize(100L)
                //设置过期时间
                .expireAfterAccess(30L, TimeUnit.SECONDS)

                .build(new CacheLoader<Integer,String>() {
                    @Override
                    public String load(Integer str) throws Exception {
                        String data = dataDao.getData(str);
                        return data;
                    }
                });
        return build;
    }

    @Bean("cache")
    public Cache<Integer,String> createCache(){
        return CacheBuilder.newBuilder().build();
    }


}
