package com.springcloud.client.controller;

import com.springcloud.client.feign.Client2Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    Client2Feign client2Feign;

    @GetMapping("/getFeignTest")
    public String getFeignTest(){
        return client2Feign.feignTest("Test");
    }
}
