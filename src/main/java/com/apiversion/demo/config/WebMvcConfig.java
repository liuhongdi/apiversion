package com.apiversion.demo.config;

import com.apiversion.demo.requestmapping.ApiVersionRequestMappingHandlerMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    //在获取RequestMappingHandlerMapping时
    //返回我们自定义的ApiVersionRequestMappingHandlerMapping
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new ApiVersionRequestMappingHandlerMapping();
    }
}