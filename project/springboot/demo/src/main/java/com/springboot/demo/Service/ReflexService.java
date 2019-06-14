package com.springboot.demo.Service;

import com.springboot.demo.Interface.ReflexBaseImp;
import com.springboot.demo.Interface.impl.ReflexClassA;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Service
public class ReflexService {

    //实现反射动态类加载(好处:代码灵活性加强,降低耦合(比工厂模式要强))
    public void ExecuteReflex(String classParam){
        try {
            ReflexClassA f = new ReflexClassA();
            // 第一种表达方式
            Class c1 = ReflexClassA.class;// 这种表达方式同时也告诉了我们任何一个类都有一个隐含的静态成员变量class
            // 第二种表达方式
            Class c2 = f.getClass();// 这种表达方式在已知了该类的对象的情况下通过getClass方法获取

            //第三种表达方式，Class.forName
            //要点:Class是所有类的对象,new对象只是静态加载出Class的实例对象,因为Class可以反过来获取每个类的对象,称为类类型
            Class cs = Class.forName(classParam);

            //注意：要动态加载类，必须都实现同一个接口,就可以强转为这个接口类型的对象,
            //因为子类都实现了ReflexBaseImp接口，所以所有属性和方法都实现了,所有属性和方法都存在,接下来就可以调用了
            ReflexBaseImp reflexClass = (ReflexBaseImp)cs.newInstance();  //newInstance()只能调用无参构造方法。
            reflexClass.GetReflex("test1", "test2"); //执行实例化后的类实现的方法

            ///////反射获取类的属性以及方法
            //获取类名称
            System.out.println(cs.getName());

            //获取类方法
            GetClassMethod(cs);

            //获取类中变量
            GetClassField(cs);

            //获取类构造方法
            GetClassConstructor(cs);

            //重点！！！
            //方法的放射操作(好处:可以动态传入想执行的方法,灵活性)
            ReflexInvoke(cs, reflexClass);

            //反射中的泛型对象
            //注意: 泛型的作用是在编译阶段防止错误输入，绕过编译就绕过了泛型，
            //      反射都是绕过编译操作，因此在反射中ArrayList<String>使用add添加int类型也不报错
            ReflexFanXing();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReflexFanXing() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("test1");
        ArrayList arrayList2 = new ArrayList<>();

        //抽出类类型
        Class cs1 = arrayList1.getClass();
        Class cs2 = arrayList2.getClass();

        //结果是true说明类类型是一样的，编译后是去泛型化的
        System.out.println(cs1==cs2);

        Method arrayMethod = cs1.getMethod("add", new Class[]{ Object.class});
        arrayMethod.invoke(arrayMethod, 100); //因为反射去泛型化,所以可以添加非String类型数据
        System.out.println(arrayList1);
    }

    public void ReflexInvoke(Class cs, ReflexBaseImp reflexClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //先获取类的Method对象,然后传入方法名以及参数（1.方法名,2:参数）
        Method methodTest = cs.getMethod("GetReflex", new Class[]{ String.class,String.class }); //方法名+参数类型
        Object o1 = methodTest.invoke(reflexClass, "invoke1","invoke2"); //实例化的对象+参数,即可调用方法,跟实例化对象调用方法一致,也可以有返回值
        Object o2 = methodTest.invoke(reflexClass, new Object[]{"invoke3","invoke4"}); //推荐使用这种参数方式，最好是外部方法直接传进来
    }

    public void GetClassConstructor(Class cs) {
        Constructor[] cr = cs.getDeclaredConstructors(); //因为构造函数都是某个类自己中存在的所以使用getDeclaredConstructors
        for (Constructor constructor : cr){
            //1.获取构造方法名称
            String constructorName = constructor.getName();
            //2.获取构造方法变量类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class class1 : paramTypes){
                System.out.println(class1.getName());
            }
        }
    }

    public void GetClassMethod(Class cs) throws NoSuchMethodException {
        //获取所有方法(数组形式)
        Method[] methods = cs.getMethods(); //只包含public方法
        for(int i=0;i<methods.length;i++){
            //获取方法中的信息
            //1.获取方法返回类型的类类型
            Class returnTyp = methods[i].getReturnType();
            System.out.println(returnTyp.getName());
            //2.获取方法名
            System.out.println(methods[i].getName());
            //3.获取方法参数类型
            Class[] paramTypes = methods[i].getParameterTypes();
            for (Class class1 : paramTypes){
                System.out.println(class1.getName());
            }
        }

        //获取类自身声明的所有方法(包括私有方法)
        Method selfMethod =  cs.getDeclaredMethod("SelfMethod");
    }

    public void GetClassField(Class cs) {
        Field[] fs = cs.getFields(); //获取所有成员变量方法(注意:因为cs的接口类没有包含字段,所以fs无法获取到字段变量要使用getDeclaredFields)
        Field[] fs1 = cs.getDeclaredFields(); //获取自己成员变量方法(包含私有)
        for (Field field : fs1){
            //1.获取变量类型
            Class fieldType = field.getType();
            String typeName = fieldType.getName();
            //2.获取变量名称
            String filedName = field.getName();
        }
    }
}


//newInstance和new
//1.类的加载方式不同
//在执行Class.forName("a.class.Name")时，JVM会在classpath中去找对应的类并加载，这时JVM会执行该类的静态代码段。
//在使用newInstance()方法的时候，必须保证这个类已经加载并且已经连接了，而这可以通过Class的静态方法forName()来完成的。
//使用关键字new创建一个类的时候，这个类可以没有被加载，一般也不需要该类在classpath中设定，但可能需要通过classlaoder来加载。
//2.所调用的构造方法不尽相同
//new关键字能调用任何构造方法。
//newInstance()只能调用无参构造方法。
//3.执行效率不同
//new关键字是强类型的，效率相对较高。
//newInstance()是弱类型的，效率相对较低。