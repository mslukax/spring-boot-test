package com.springboot.demo.Service;

import org.springframework.stereotype.Service;

//单例模式
@Service
public class SingleCaseService {

    //饿汉单例模式（程序开启就生产对象）
    private static SingleCaseService singlecase = new SingleCaseService();

    public SingleCaseService(){
        System.out.println("This is SingleCase,单例模式");
    }

    public static SingleCaseService getSinglecase() {
        return singlecase;
    }

    //懒汉单例模式(程序调用时才生产对象,建议使用)
    private static SingleCaseService singlecase2 = null;
    public static SingleCaseService getSinglecase2() {
        if(null==singlecase2) {
            singlecase2 = new SingleCaseService();
        }
        return singlecase2;
    }
}
