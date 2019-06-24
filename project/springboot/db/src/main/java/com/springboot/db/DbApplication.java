package com.springboot.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class DbApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
        System.out.println("db项目运行成功！！！！");
    }

}
