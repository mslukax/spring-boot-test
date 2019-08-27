package com.springboot.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component //导入依赖容器中
//服务请求日志Aop
public class RequestLog {
    private static final Logger logger = LoggerFactory.getLogger(RequestLog.class);

    @Pointcut("execution(* com.springboot.demo..*.*(..))") //绑定要切入的层
    public void ControllerLog(){}

    @Before("ControllerLog()") //请求前时写log
    public void doBeforeRequest(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL:" +request.getRequestURI());
    }


    @AfterReturning(returning =  "ret", pointcut = "ControllerLog()") //返回后写log
    public void doAfterReturning(Object ret){
        logger.info("Response : " + ret);
    }
}

//AOP实现原理(jdkProxy和Cglib)
//jdkProxy：通过java内部放射机制实现(如果是接口使用该方式实现)
//核心：InvocationHandler接口和Proxy类

//Cglib：借助ASM实现(以继承的方式动态生成目标类的代理)
//注意：因为继承方式，所以类如果是final类型的是无法生成的，
//该方式先当于在作用类中生成一个子类继承，以代理的方式执行aop切面代码。

//代理模式：接口+真实实现类+代理类
//public interface Payment{
//    void pay();
//}
//
//public class PayProxy implements Payment{
//    private PayBean payBean;
//
//    public void beforePay(){
//        System.out.println("支付的之前的日志代码");
//    }
//
//    public void afterPay(){
//        System.out.println("支付的之后的日志代码");
//    }
//
//    @Override
//    public void pay(PayBean payBean){
//        this.payBean = payBean;
//        beforePay();
//        payBean.pay();
//        afterPay();
//    }
//}
//
//public class PayBean implements Payment{
//    @Override
//    public void pay(){
//        System.out.println("支付的业务代码");
//    }
//}

//使用PayProxy代理之后 PayProxy proxy = new PayProxy(new PayBean()); proxy.pay();
//就可以实现  beforePay();  afterPay();与业务无关的日志操作。

//Spring里面的代理模式实现：
//1.真实实现类的逻辑包含在getBean方法里面
//2.getBean方法返回的实际上是Proxy的实例(如果是使用了AOP，则该类已经备注处理过，成为代理实例)
//3.Proxy实例是Spring采用JDK Proxy和Cglib动态生成。
//注意：因为在spring中使用时ioc容器里面的bean进行代理模式处理，所以要确保该实例已经注入到spring中
