package com.github.wxiaoqi.gate.back.config;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * mybatis mapper 扫描配置类
 * 
 * @author wanghaobin
 * @date 2016年12月15日
 * @since 1.7
 */
@Configuration
@Data
@Slf4j
@Component
public class MapperConfiguration implements EnvironmentAware {


//    @Value("${mybatis.basepackage}")
//    private String basePackage;
    private Environment evn;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(evn.getProperty("mybatis.basepackage"));
        return mapperScannerConfigurer;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.evn = environment;
    }
}