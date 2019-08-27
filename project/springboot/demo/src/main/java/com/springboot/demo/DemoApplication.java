package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("demo项目运行成功！！！！");
    }
}


//springboot概念(约定大于配置)
//优点:
//1.独立运行
//Spring Boot而且内嵌了各种servlet容器，Tomcat、Jetty等，现在不再需要打成war包部署到容器中，
//Spring Boot只要打成一个可执行的jar包就能独立运行，所有的依赖包都在一个jar包内。
//2.简化配置
//spring-boot-starter启动器自动依赖其他组件，简少了maven的配置。
//3.自动配置(注解)
//Spring Boot能根据当前类路径下的类、jar包来自动配置bean，如添加一个spring-boot-starter-web启动器就能拥有web的功能，无需其他配置。
//4.无代码生成和XML配置
//Spring Boot配置过程中无代码生成，也无需XML配置文件就能完成所有配置工作，这一切都是借助于条件注解完成的。
//5.应用监控
//Spring Boot提供一系列端点可以监控服务及应用，做健康检测。






