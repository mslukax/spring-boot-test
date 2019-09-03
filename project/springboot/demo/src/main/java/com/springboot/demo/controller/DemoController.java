package com.springboot.demo.controller;

import com.springboot.demo.datacalc.MySort;
import com.springboot.demo.entity.TestEntity;
import com.springboot.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController

public class DemoController {

    @Autowired
    EnumService enumService;

    @Autowired
    StaticClassService staticClassService;

    @Autowired
    CollectionMapService collectionMapService;

    @Autowired
    ReflexService reflexService;

    @Autowired
    ProcessService processService;

    @Autowired
    ProcessPoolService processPoolService;

    @Autowired
    MySort mySort;

    @RequestMapping("/")
    public String Home(){

        Map<Integer, TestEntity> map = new HashMap<>(); //无序
        map.put(1, new TestEntity("id1","name1"));
        map.put(1, new TestEntity("id2","name2"));


        //Integer与int比较
        //注意Integer之间比较最好用equals，才能比较content值

        //1.两Integer比较
        Integer int1 = new Integer(1);
        Integer int2 = new Integer(1);
        System.out.println(int1==int2); //false，两个内存地址不一致，是两个对象。

        //2.int与Integer比较
        int int3 = 1;
        System.out.println(int1==int3); //true, Integer跟int比较时候，会自动将里面的int值与 int基本类型数据进行比较

        //3.Integer基本类型与Integer对象比较
        Integer int4 = 1;
        System.out.println(int1==int3);//false，Integer使用基本数据类型跟new Integer()也是两个内存地址不一致的对象。

        //4.Integer基本类型与Integer基本类型比较
        Integer int5 = 1;
        System.out.println(int4==int5);//true，Integer使用基本数据类型，如果值在-128~127之间，将会存在一个缓存对象中，int3跟int4是同一个对象。
        Integer int6 = 128;
        Integer int7 = 128;
        System.out.println(int4==int5);//false，值超过-128~127之间



        //String类型比较
        //将String作为对象使用
        String s1 = new String("string");
        String s2 = new String("string");
        System.out.println(s1==s2); //false, 因为new创建了2个String对象，是2个不同内存地址对象
        System.out.println(s1.equals(s2)); //true，equals比较String对象中的值content

        //将String作为基本类型使用
        String s3 = "string";
        String s4 = "string";
        System.out.println(s3==s4);//true，String缓冲池内存在与s4值相同的String对象时，将不会创建新的String对象。所以s3==s4
        System.out.println(s3.equals(s4));//true
        System.out.println(s3==s1);//true,因为s1是强制new出来的新对象，所以不是同一个对象，跟缓存池里也是两个不同的对象。

//        String date = DataConvert.GetDataStr(new Date(), "yyyy-MM-dd");
//
//        collectionMapService.QueryList();
//        collectionMapService.SetMap();

//        reflexService.ExecuteReflex("com.springboot.demo.Interface.impl.ReflexClassA");

        //processService.StartAndRun();
        //processService.ThreadAndRunabled();

//        try {
//           processService.GetThreadValue();
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        processService.SleepAndWait();
//        processService.NotifyAndNotifyAll();
        //processService.SynchronizedTest();

        //processPoolService.ThreadPoolExecutor();

        //enumService.GetDay();

        //staticClassService.ExecStaticMethod();
        return "This is Spring Boot";
    }



}
