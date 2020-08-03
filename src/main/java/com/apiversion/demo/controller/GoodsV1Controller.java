package com.apiversion.demo.controller;

import com.apiversion.demo.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//整个controller都匹配/v1/goods/*
@ApiVersion(1)
@RestController
@RequestMapping("/{version}/goods")
public class GoodsV1Controller {

    @GetMapping
    @RequestMapping("/goodsone")
    public String goodsone(@PathVariable String version) {
        return "goods goodsone v1 : version:" + version;
    }
}