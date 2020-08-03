package com.apiversion.demo.controller;

import com.apiversion.demo.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{version}/home")
public class HomeController {

    //匹配版本v1的访问
    @ApiVersion(1)
    @GetMapping
    @RequestMapping("/home")
    public String home01(@PathVariable String version) {
        return "home v1 : version:" + version;
    }

    //匹配版本v2的访问
    @ApiVersion(2)
    @GetMapping
    @RequestMapping("/home")
    public String home02(@PathVariable String version) {
        return "home v2 version: " + version;
    }
}