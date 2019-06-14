package com.springboot.demo.Interface.impl;

import com.springboot.demo.Interface.ReflexBaseImp;

//实现接口反射类Bs
public class ReflexClassB implements ReflexBaseImp {
        @Override
        public void GetReflex(String str1, String str2) {
            System.out.println("This is ReflexClassB");
        }
}
