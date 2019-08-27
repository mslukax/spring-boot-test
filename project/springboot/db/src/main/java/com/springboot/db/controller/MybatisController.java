package com.springboot.db.controller;

import com.springboot.db.entity.MybatisEntity;
import com.springboot.db.mybatis.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/mybatis")
public class MybatisController {
    @Autowired
    MybatisService mybatisService;

    @RequestMapping(value = "/Getmybatis", method = RequestMethod.POST)
    public List<MybatisEntity> Getmybatis(){
        //mybatisService.GetMybatisData();
        //mybatisService.Transaction();
        return mybatisService.GetMybatisData();
    }
}
