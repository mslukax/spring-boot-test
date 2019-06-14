package com.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class TryCatchController {


    //Throwsable是java中异常父类,Error和Exception是子类
    //Error即使java虚拟机报错,等外部错误,跟程序无关，主要考究Exception

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
//       catch (FileNotFoundException e){ //注意,编写catch异常捕捉块必须从子Exception到父的Exception,否则回报错。如下报错
//           System.out.println("文件无法找到");
//           e.printStackTrace();//打印调用堆栈
//       }
       //finally，最终执行的代码段，例如用于日志存储，或者数据库连接关闭等
        finally {

       }

    }

    @RequestMapping("/throws")
    //方法后面的throws Exception直接把异常提交给上层代码处理
    public void Throws() throws Exception {
        //throw new Exception,主动报异常
        throw new Exception("This is throw new Exception()");
    }

}


