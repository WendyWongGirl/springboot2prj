package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Mybatis plus配置（如：拦截器...）
 */
@Configuration
public class MybatisplusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //Add Page interception
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //...There also can add other type interception
        return interceptor;
    }
}
