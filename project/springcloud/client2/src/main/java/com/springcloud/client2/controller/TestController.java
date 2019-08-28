package com.springcloud.client2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //被其他服务调用的测试方法
    @GetMapping("/feignTest")
    public String feignTest(String name){
        return name + ":FeignTest2";
    }
}
