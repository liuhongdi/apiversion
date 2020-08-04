package com.apiversion.demo.requestmapping;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//实现RequestCondition
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    //api版本号
    private String apiVersion;
    //版本号的格式，如: /v[1-n]/api/test or /v1.5/home/api
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/v((\\d+\\.\\d+)|(\\d+))/");
    public ApiVersionCondition(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    //将不同的筛选条件进行合并
    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.getApiVersion());
    }

    //版本比对，用于排序
    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        //优先匹配最新版本号
        return compareTo(other.getApiVersion(),this.apiVersion)?1:-1;
    }

    //获得符合匹配条件的ApiVersionCondition
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        //System.out.println("m:"+m);
        if (m.find()) {
            String version = m.group(1);
            //System.out.println("version:"+version);
            if (compareTo(version,this.apiVersion)){
                return this;
            }
        }
        return null;
    }
    //compare version
    private boolean compareTo(String version1,String version2){
        if (!version1.contains(".")) {
            version1 += ".0";
        }
        if (!version2.contains(".")) {
            version2 += ".0";
        }
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        for (int i = 0; i < split1.length; i++) {
            if (Integer.parseInt(split1[i])<Integer.parseInt(split2[i])){
                return false;
            }
        }
        return true;
    }

    public String getApiVersion() {
        return apiVersion;
    }
}