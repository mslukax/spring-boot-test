package com.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class TryCatchController {

    @RequestMapping("/trycatch")
    public void TryCatch() {
       try{
           String str = "test";
           int i =  Integer.parseInt(str);

           File file = new File("d:/LOL.exe");
           new FileInputStream(file);
       }
       //只会有一种catch会被执行,即是要么是FileNotFoundException要么是Exception
       catch (FileNotFoundException e){
           System.out.println("文件无法找到");
           e.printStackTrace();//打印调用堆栈
       }
       catch (Exception e){
           System.out.println("代码报错");
           e.printStackTrace();//打印调用堆栈
       }
       //finally，最终执行的代码段，例如用于日志存储，或者数据库连接关闭等
        finally {

       }

    }

}


