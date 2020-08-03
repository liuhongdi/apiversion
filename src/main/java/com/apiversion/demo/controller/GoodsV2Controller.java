package com.apiversion.demo.controller;

import com.apiversion.demo.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//整个controller都匹配/v2/goods/*
@ApiVersion(2)
@RestController
@RequestMapping("/{version}/goods")
public class GoodsV2Controller {

    @GetMapping
    @RequestMapping("/goodsone")
    public String goodsone(@PathVariable String version) {
        return "goods goodsone v2 : version:" + version;
    }
}