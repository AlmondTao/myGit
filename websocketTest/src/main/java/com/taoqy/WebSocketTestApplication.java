package com.taoqy;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;

@SpringBootApplication
public class WebSocketTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketTestApplication.class,args);
    }
}
