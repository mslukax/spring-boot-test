package com.springboot.demo.Service;

public class JvmService {
    //资料地址 https://www.cnblogs.com/andy-zhou/p/5327288.html

    //1.概念了解
    //是什么
    //栈是运行时的单位，而堆是存储的单位。（栈代表了处理逻辑，而堆代表了数据）
    //栈解决程序的运行问题，即程序如何执行，或者说如何处理数据；堆解决的是数据存储的问题，即数据怎么放、放在哪儿。
    //栈只能向上增长，因此就会限制住栈存储内容的能力。而堆不同，堆中的对象是可以根据需要动态增长的。（相应栈中只需记录堆中的一个地址即可）

    //有什么用
    //栈因为是运行单位，因此里面存储的信息都是跟当前线程（或程序）相关信息的。包括局部变量、程序运行状态、方法返回值等等；(相当于一个线程)
    //而堆只负责存储对象信息。（堆是所有线程共享的）

    //堆中存什么？栈中存什么？
    //堆中存的是对象。栈中存的是基本数据类型和堆中对象的引用。(基本数据类型大小固定)

    //堆和栈中，栈是程序运行最根本的东西。程序运行可以没有堆，但是不能没有栈。而堆是为栈进行数据存储服务，说白了堆就是一块共享的内存。
    //不过，正是因为堆和栈的分离的思想，才使得Java的垃圾回收成为可能。


    //java对象的大小，例子：
    //Class NewObject {
    //    int count;
    //    boolean flag;
    //    Object ob;
    //}
    //其大小为：空对象大小(8byte)+int大小(4byte)+Boolean大小(1byte)+空Object引用的大小(4byte)=17byte。
    //但是因为Java在对对象内存分配时都是以8的整数倍来分，因此大于17byte的最接近8的整数倍的是24( 17 < 8*3,按8的倍数向上运算)，因此此对象的大小为24byte。

}
