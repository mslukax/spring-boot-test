package com.springboot.demo.controller;

import com.springboot.demo.Entity.TestEntity;
import com.springboot.demo.Service.*;
import com.springboot.demo.bean.DataConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;

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


    @RequestMapping("/")
    public String Home(){

        Integer test1 = new Integer(1);
        Integer test2 = new Integer(1);
        System.out.println(test1.equals(test2));

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

        processPoolService.ThreadPoolExecutor();
        return "This is Spring Boot";
    }

    @RequestMapping("/Getday")
    public String Getday(){
        return enumService.GetDay();
    }

    @RequestMapping("/StaticClass")
    public void StaticClassService(){
        staticClassService.ExecStaticMethod();
    }

}
