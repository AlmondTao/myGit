package com.taoqy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * zuul-ratelimit 原理是利用了zuulfilter，通过创建pre zuulfilter 和post zuulfilter来实现各种策略
 * 使用者可以通过自定义DefaultRateLimitKeyGenerator 里的key方法来确定请求者的唯一性
 * 通过配置repository 来存储请求者的请求次数，响应时间以及刷新时间。
 *
 * @author Taoqy
 * @version 1.0, 2018/12/24
 * @see [相关类/方法]
 */
//enableZuulServer 如果调用的服务不是eureka中心上的可以使用
//@EnableZuulServer
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);

    }
}
