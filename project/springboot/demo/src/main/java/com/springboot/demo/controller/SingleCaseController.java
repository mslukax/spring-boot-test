package com.springboot.demo.controller;

import com.springboot.demo.Service.SingleCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingleCaseController {

   @Autowired
   SingleCaseService singleCaseService;

    @RequestMapping("/singlecase")
    public String SingleCase(){

        SingleCaseService test1 = singleCaseService.getSinglecase();
        SingleCaseService test2 = singleCaseService.getSinglecase();

        SingleCaseService test3 = singleCaseService.getSinglecase2();
        SingleCaseService test4 = singleCaseService.getSinglecase2();

        System.out.println(test1 == test2); //单例模式中，生成的对象都一致，为true
        System.out.println(test3 == test4); //单例模式中，生成的对象都一致，为true
        return null;
    }
}
