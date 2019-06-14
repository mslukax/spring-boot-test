package com.springboot.demo.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

//数据转换类
public class DataConvert<T> {

    public DataConvert(){
        System.out.println("数据转换类");
    }

    //获取日期自定义String类型
    public static String GetDataStr(Date datetime, String type){
        if(type == null || datetime == null) return "";
        SimpleDateFormat dateType = new SimpleDateFormat(type);
        return dateType.format(datetime);
    }

    //String转int
    public static int StringToInt(String str){
       if(str == null) return 0;
       return Integer.parseInt(str);
    }

    //int转String
    public static String IntToString(int i){
        return String.valueOf(i);
    }


//   1.Math.round()：根据“round”的字面意思“附近、周围”，可以猜测该函数是求一个附近的整数，看下面几个例子就明白。
//
//    小数点后第一位<5
//    正数：Math.round(11.46)=11
//    负数：Math.round(-11.46)=-11
//
//    小数点后第一位>5
//    正数：Math.round(11.68)=12
//    负数：Math.round(-11.68)=-12
//
//    小数点后第一位=5
//    正数：Math.round(11.5)=12
//    负数：Math.round(-11.5)=-11
//    总结：（小数点后第一位）大于五全部加，等于五正数加，小于五全不加。
//
//            2.Math.ceil()：根据“ceil”的字面意思“天花板”去理解；
//    例如：
//            Math.ceil(11.46)=Math.ceil(11.68)=Math.ceil(11.5)=12
//            Math.ceil(-11.46)=Math.ceil(-11.68)=Math.ceil(-11.5)=-11
//
//            3.Math.floor()：根据“floor”的字面意思“地板”去理解；
//    例如：
//            Math.floor(11.46)=Math.floor(11.68)=Math.floor(11.5)=11
//            Math.floor(-11.46)=Math.floor(-11.68)=Math.floor(-11.5)=-12
}
