package com.springboot.db.controller;

import com.springboot.db.mybatis.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MybatisController {
    @Autowired
    MybatisService mybatisService;

    @RequestMapping("/mybatis")
    public void Getmybatis(){
        //mybatisService.GetMybatisData();
        mybatisService.Transaction();
    }
}
