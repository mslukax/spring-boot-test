package com.springboot.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class StaticClassService {
      public void ExecStaticMethod(){
            //1.静态内部类,静态方法!
            Test05.StaticClass.show();
            //new Test05.StaticClass().show();
            //被注释的这里其实是合法的,只是有点多余,你看完整个在回头看这里,这与调用静态方法有违背!

            //2.非静态内部类,非静态方法!
            new Test06().new StaticClass().show();

            //3.静态内部类,非静态方法!
            new Test07.StaticClass().show();

            //4.本类中的静态内部类,此时他只是一个自己的成员与3相比一下!
            new StaticClassService.StaticClass().show();

            //5:局部匿名内部类!
            show();

        /*总结一下:你知道,静态方法被访问的时候,只需要: [类名.静态方法] 就可以被访问!
        情况1.不用new,就可以访问!
        情况2.都是非静态.所以都的new
        情况3:为什么要new,是因为你的方法是非静态的,如果是静态,就如同情况1;
        情况4:与3是一样!
        情况5:属于匿名内部类,在方法里面的!
        情况6:就是情况1被注释的部分,它就等价,new了一个静态类,静态类也可以被new只是有点多余,因为他已经存在了!*/

        }//情况4:
        static class StaticClass{
            void show() {
                System.out.println("成员属性静态内部类!");
            }
        }//情况5:
        static void show() {
            new Object(){
                void show() {
                    System.out.println("局部内部类!");
                }
            }.show();
        }
    }
    //情况1:
    class Test05{
       static String id = StaticClass.id2;
        static class StaticClass{//静态类!
           static String id2 = Test05.id; //静态内部类只能访问外部类的静态成员！！！！
            static void show() {//静态类中的静态方法!
                System.out.println("静态内部类,静态方法!");
            }
        }
    }//情况2:
    class Test06{
        String id = new StaticClass().id2;
        class StaticClass{//非静态类
            String id2 = new Test06().id;
            void show() {//非静态方法!
                System.out.println("非静态内部类,非静态方法!");
            }
        }
    }//情况3:
    class Test07{
    static class StaticClass{//静态类!
          void show() {//静态类中的非静态方法!
            System.out.println("静态内部类,非静态方法!"); //报错
        }
    }
}

////情况4: 非静态内部类不能编写静态方法，但是静态内部类可以写静态和非静态方法
// 其实归根结底，还是类与对象的区别，静态属性不依赖于对象，所以访问修改的时候不需要依赖当前有没有存活的对象。
//  内部类其实也可以认为是外部类的一个成员变量，想要访问内部类，必须先实例化外部类，然后通过外部类才能访问内部类。
//  只要是成员变量，就需要依赖具体的对象，这个时候如果在非静态内部类里面声明静态属性就会破坏了这一逻辑，
//  所以java语言在语义层面不允许我们那么做，这其实不是技术问题，是一个语言的逻辑和语义问题。

//class Test08{
//    static class StaticClass{//静态类!
//        void show() {//非静态类中的非静态方法!报错
//            System.out.println("静态内部类,非静态方法!");
//        }
//    }
//}


