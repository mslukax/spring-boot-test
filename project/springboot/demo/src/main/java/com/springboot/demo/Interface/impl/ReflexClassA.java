package com.springboot.demo.Interface.impl;

import com.springboot.demo.Interface.ReflexBaseImp;

//实现接口反射类A
public class ReflexClassA implements ReflexBaseImp {

    private String id;

        @Override
        public void GetReflex(String str1, String str2) {
            System.out.println("This is ReflexClassA。" + str1 + str2);
        }

        private void SelfMethod(){
            System.out.println("This is SelfMethod");
        }
}
