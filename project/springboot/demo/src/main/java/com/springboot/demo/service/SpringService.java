package com.springboot.demo.service;

public class SpringService {

    //IOC控制反转,代码实现

    public  SpringService() {
        //非控制反转写法
        int size = 1000;
        Carbody carbody = new Carbody(size);

        //反转控制写法
        Wheel1 wheel1 = new Wheel1(size);
        Carbody1  carbody1 = new Carbody1(wheel1);
    }


    //非控制反转写法(车子依赖于轮子实现,轮子如果有参数改动(添加了size)，父类要跟着改)
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

//1.IOC，DI(依赖注入)，DL(依赖查找)关系
//IOC根据 DI(依赖注入)，DL(依赖查找)实现。
//DL(依赖查找)：需要自己调用spring框架中的API，根据配置去查找对应的依赖并且注入(不适用)。
//DI(依赖注入)：有以下四种注入方式
//   (1).set注入:
//    //注入对象springDao
//    private SpringDao springDao;
//        //一定要写被注入对象的set方法
//        public void setSpringDao(SpringDao springDao) {
//        this.springDao = springDao;
//    }

//   (2).构造器注入(就是构造函数中set要注入的对象)
//     注入对象springDao
//    private SpringDao springDao;
//    private User user;
//
//    public SpringAction(SpringDao springDao,User user){
//        this.springDao = springDao;
//        this.user = user;
//        System.out.println("构造方法调用springDao和user");
//    }

//   (3).接口注入(实现接口并且注入)
//    public class FactoryDao {
//
//        public FactoryDaoImpl getFactoryDaoImpl(){
//            return new FactoryDaoImpl();
//        }
//    }

///  (4).注解注入(推荐使用)
//   主要有四种注解可以注册bean，每种注解可以任意使用，只是语义上有所差异：
//   @Component：可以用于注册所有bean
//   @Repository：主要用于注册dao层的bean
//   @Controller：主要用于注册控制层的bean
//   @Service：主要用于注册服务层的bean

//2.IOC BeanFactory(spring框架最核心的接口)
//(1).提供ioc的配置机制
//(2).包含Bean的各种定义，便于实例化bean
//(3).建立bean之间的依赖关系
//(4).bean生命周期的控制

//3.BeanFactory和ApplicationContent
//BeanFactory负责对spring内部提供接口服务，底层服务
//ApplicationContent对spring使用者提供接口服务

//4.spring bean的生命周期(简单版)
//大概分为4个阶段：
//(1)bean的实例化
//(2)bean的初始化，包括初始化方法和属性填充
//(3)bean的调用
//(4)bean的销毁






