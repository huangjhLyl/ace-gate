package com.github.wxiaoqi.gate.back.config;

import com.github.wxiaoqi.gate.agent.rest.ApiInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ace on 2017/7/6.
 */
@Configuration
public class ApiWebAppConfig implements WebMvcConfigurer {
    @Value("${gate.client.authHost}")
    private String authHost;
    @Value("${gate.client.authHeader}")
    private String authHeader;

    //静态资源不进行拦截
    private static List<String> EXCULDE_PATH = Arrays.asList("/**","/login/**","/index/**","/ag/**","/css/**"
            ,"/datas/**","/face/**","/font/**","/fonts/**","/images/**","/js/**"
            ,"/lay/**","/modules/**","/plugins/**");
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiInterceptor(authHost,authHeader)).addPathPatterns("/**")
                .excludePathPatterns(EXCULDE_PATH);
    }
}
