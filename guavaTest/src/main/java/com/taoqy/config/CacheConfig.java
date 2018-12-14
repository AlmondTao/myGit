package com.taoqy.config;

import ch.qos.logback.core.util.TimeUtil;
import com.google.common.cache.*;
import com.taoqy.dao.DataDao;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Bean("loadingCache")
    public LoadingCache createLoadingCache(DataDao dataDao){
        LoadingCache<Integer,String> build = CacheBuilder.newBuilder()
                //设置最大数量
                .maximumSize(100L)
                //设置过期时间
                //access使用 = Write/Update/Read
                .expireAfterAccess(30L, TimeUnit.SECONDS)

                //基于引用的回收
                //1.弱引用存储健回收，当健没有其他(强或软)引用时，缓存项可以被回收
                .weakKeys()
                //2.弱引用存储值回收，当值没有其他(强或软)引用时，缓存项可以被回收
                .weakValues()
                //3.使用软引用存储值，软引用只有在响应内存需要时，才按照全局最近最少使用的顺序回收
//                .softValues()

                //设置权重
//                .weigher(new Weigher<Integer, String>() {
//                    @Override
//                    public int weigh(Integer key, String value) {
//                        //返回权重
//                        return 0;
//                    }
//                })
                //设置过期时间
                //write = Write/Update
                .expireAfterWrite(30L, TimeUnit.SECONDS)

                .refreshAfterWrite(30L, TimeUnit.SECONDS)
                //创建缓存
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
