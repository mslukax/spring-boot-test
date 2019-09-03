package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


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
//极大的简化了xml配置，bean的注入，依赖包的注入。

//springboot核心配置(@SpringBootApplication)
//SpringBootApplication包含以下3个注解: 其实这些注解都是自动生成配置实现注入功能(无xml，扫描，自动配置)
//1.@Configuration：实现配置文件功能。用来代替applicationContext.xml(spring.xml)配置文件，
//所有这个配置文件里面能做到的事情都可以通过这个注解所在类来进行注册,例如@Bean,代替xml中<bean>配置进行注入一样;
//2.@ComponentScan：Spring组件扫描。代替配置文件中<component-scan>配置，开启组件扫描，即自动扫描包路径下的注入注解(@Component,@Service等)，
//实现bean实例的注入。
//3.@EnableAutoConfiguration：




