package org.demo.base.config;

import org.demo.interceptor.MyInterCeptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 用来处理拦截业务的类
     * @return
     */
    @Bean
    MyInterCeptor myInterCeptor(){
        return new MyInterCeptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(myInterCeptor()).addPathPatterns("/**");
    }
}
