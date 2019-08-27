package com.springboot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        //非反转控制写法
        int size = 1000;
        Carbody carbody = new Carbody(size);

        //反转控制写法
        Wheel1 wheel1 = new Wheel1(size);
        Carbody1  carbody1 = new Carbody1(wheel1);
    }

    //非反转控制写法(车子依赖于轮子实现,轮子如果有参数改动(添加了size)，父类要跟着改)
    public class Carbody{
        private Wheel wheel;
        public Carbody(int size){
            this.wheel = new Wheel(size);
        }
    }

    public class Wheel{
        private int size;
        public Wheel(int size){
            this.size = size;
            //this.size = 20;
        }
    }

    //控制反转写法，不管下层对象有什么改动，整个对象都传过来，不管你改了什么
    public class Carbody1{
        private Wheel1 wheel1;
        public Carbody1(Wheel1 wheel1){
            this.wheel1 = wheel1;
        }

    }

    public class Wheel1{
        private int size;
        public Wheel1(int size){
            this.size = size;
        }
    }

}
