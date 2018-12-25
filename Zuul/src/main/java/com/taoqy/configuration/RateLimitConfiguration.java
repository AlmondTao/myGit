package com.taoqy.configuration;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.DefaultRateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2018/12/25
 * @see [相关类/方法]
 */
@Configuration
public class RateLimitConfiguration {

    /**
     *
     * @param properties
     * @return
     * @see com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.RateLimitAutoConfiguration
     */
    @Bean
    public DefaultRateLimitKeyGenerator createDefaultRateLimitKeyGenerator(final RateLimitProperties properties){
        return new DefaultRateLimitKeyGenerator(properties){
            @Override
            public String key(HttpServletRequest request, Route route, RateLimitProperties.Policy policy) {
                //定义key，标识访问
                return super.key(request, route, policy)+":"+request.getParameter("name");
            }
        };
    }
}
