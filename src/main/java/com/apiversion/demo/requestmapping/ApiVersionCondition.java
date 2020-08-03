package com.apiversion.demo.requestmapping;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//实现RequestCondition
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    //版本号的格式，如: /v[1-n]/api/test
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v(\\d+)/");

    private int apiVersion;

    public int getApiVersion() {
        return this.apiVersion;
    }
    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    //方法上注释的 @ApiVersion 会覆盖 类定义的 @ApiVersion
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.getApiVersion());
    }

    //获得符合匹配条件的ApiVersionCondition
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if (m.find()) {
            int version = Integer.valueOf(m.group(1));
            if (version >= getApiVersion()) {
                return this;
            }
        }
        return null;
    }

    //有多个符合匹配条件的ApiVersionCondition，版本号较大的优先匹配
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return other.getApiVersion() - getApiVersion();
    }
}