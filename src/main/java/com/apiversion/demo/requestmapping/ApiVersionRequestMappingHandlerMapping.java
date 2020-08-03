package com.apiversion.demo.requestmapping;

import com.apiversion.demo.annotation.ApiVersion;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Objects;

//扩展RequestMappingHandlerMapping
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    //类上有 @ApiVersion注解时生效
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createRequestCondition(apiVersion);
    }

    //方法上有 @ApiVersion注解时生效
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createRequestCondition(apiVersion);
    }

    //返回ApiVersionCondition
    private RequestCondition<ApiVersionCondition> createRequestCondition(ApiVersion apiVersion) {
        if (Objects.isNull(apiVersion)) {
            return null;
        }
        int value = apiVersion.value();
        Assert.isTrue(value >= 1, "Api Version Must be greater than or equal to 1");
        return new ApiVersionCondition(value);
    }

}