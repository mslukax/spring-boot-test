package com.springboot.demo.service;

import com.springboot.demo.bean.EnumTest;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class EnumService {

    public String GetDay(){
        String day = "";
        EnumTest dayEnum = EnumTest.MON; //只获取一个枚举值

        //遍历枚举对象中的值
        //方法1
        EnumSet<EnumTest> daySet = EnumSet.allOf(EnumTest.class);
        for(EnumTest dayType : daySet){
              System.out.println(dayType.toString());
        }

        //方法2,使用values
        for(EnumTest dayType2 : EnumTest.values()){
            System.out.println(dayType2.toString());
        }
        return day;
    }
}
