package com.springboot.demo.service;

import com.springboot.demo.entity.TestEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
//集合类
public class CollectionMapService {
    //Collection为集合父类,List，Set(无序不可重复)，Quene为子类

    //List常用子类为ArrayList
    public ArrayList<TestEntity> SetList(){
        ArrayList<TestEntity> list = new ArrayList<>();
        list.add(new TestEntity("1", "test1")); //添加到集合
        list.add(0, new TestEntity("0", "test0")); //按顺序到集合,后面的list顺序会加1

        TestEntity[] array = {new TestEntity("2","test2"),new TestEntity("3","test3")};
        list.addAll(Arrays.asList(array));//Arrays.asList(array)数组转list，添加多个实体的list

        list.set(3,new TestEntity("4", "test4")); //修改一个节点
        list.remove(3); //删除某个节点
        //list.removeAll(Arrays.asList(array));//删除多个节点
        //list.removeAll(list); //删除所有节点
        return list;
    }

    //遍历方法,查询,排序等
    public void QueryList(){
        ArrayList<TestEntity>  list =  SetList();
        //遍历
        //使用Iterator迭代器遍历
        Iterator<TestEntity> it = list.iterator();
        while (it.hasNext()){
            TestEntity test = it.next();
            System.out.println("id:"+ test.getId() + ",name:" + test.getName());
        }

        for(TestEntity test : list){
            System.out.println("id:"+ test.getId() + ",name:" + test.getName());
        }

        //contain,包含查询,true or false(最好每个值去判断)
        list.contains(new TestEntity("1","test1")); //错误的查询方法，因为是new的对象，所以contain不会判断为true
        //使用循环每个值去判断
        for(TestEntity test : list){
            if(test.getId().equals("1") || test.getName().equals("test1")){
                System.out.println("id:"+ test.getId() + ",name:" + test.getName());
            }
        }

        //排序,只能排Integer和String属性类型的对象 ArrayList
        //方法1 使用TestEntity对象中实现的Comparable compareTo方法进行排序，根据id排序，比较简单
        list.add(new TestEntity("1", "Atest1"));
        Collections.sort(list);
        System.out.println("---------------------------------");
        for(TestEntity test : list){
            System.out.println("id:"+ test.getId() + ",name:" + test.getName());
        }
        //方法2 使用TestEntity对象中实现的Comparator compare方法进行排序，根据name排序
        Collections.sort(list, new TestEntity());
        System.out.println("---------------------------------");
        for(TestEntity test : list){
            System.out.println("id:"+ test.getId() + ",name:" + test.getName());
        }
    }

    public void ListTest(){
        //基本类型不能使用做泛型，只能使用object封装类
        //List<Integer> intList = new ArrayList<>(); //编译成功
        //List<int> intList = new ArrayList<>(); //编译失败
    }

    //使用Set-- HashSet(无序不可重复,散列码效率要比List高)
    public HashSet<TestEntity> SetTest() {
        HashSet<TestEntity> setList = new HashSet<>();
        TestEntity[] array = {
                new TestEntity("2","test2")
                ,new TestEntity("3","test3")
                ,new TestEntity("4","test4")
        };
        setList.add(new TestEntity("2","test2"));
        setList.add(new TestEntity("3","test3"));
        setList.add(new TestEntity("4","test4"));
        setList.add(new TestEntity("2","test2")); //存在，因为new的对象在HashSet中的equal比较中是不一样的


        HashSet<Integer> setList2 = new HashSet<>();
        setList2.add(1);
        setList2.add(2);
        setList2.add(3);
        setList2.add(1); //不存在
        setList2.add(2); //不存在
        return setList;
    }

    //Map
    public Map<Integer,TestEntity> SetMap(){
        Map<Integer, TestEntity> map = new HashMap<>(); //无序
        //新增
        map.put(1, new TestEntity("1","map1")); //使用put添加元素
        map.put(2, new TestEntity("2","map2"));
        map.put(3, new TestEntity("3","map3"));
        map.put(4, new TestEntity("4","map4"));
        map.putAll(map); //新增Map对象

        //修改(key值已存在)
        map.put(2, new TestEntity("5","map5")); //修改 map key为2的值将为替换为 id:5, name:map5

        //删除
        map.remove(2); //删除单个

        //获取所有values对象数组
        map.values();
        ArrayList<TestEntity> getList = new ArrayList<>(map.values()); //map中的value转ArrayList对象
        return map;
    }
}

//list跟set集合区别
//list实现了ArrayList,LinkedList可以重复并且有序，线程不安全；set实现了treeset，hashset，不可重复无序，线程不安全。

//ArrayList概念
//工作原理：(非线程安全，没有使用锁)
//ArrayList添加或者删除数据时,根据数据的长度，生成一个新的数组，并且赋值，从而实现动态数组。(因为数组一new出来是无法添加长度)

//Map集合概念

//1.HashMap概念以及工作原理
//HashMap由数组(默认16个数组)+链表+红黑树(链表太多时候使用)组成，非同步(线程不安全)
//注意(重点理解！！！)：
//1.这里的数组是指一个哈希散列表，一个table[index](index=hash(key)，对key使用hash算法算出值)，里面包含了Entry<k,v>就是数组的值(bucket是装Entry<k,v>数组的地方)。
//2.当HashMap产生Hash碰撞，即是put一个Entry<k,v>,key可能不一样，但是算出来的index与HashMap中的元素重复了，这时，会在该table[index]中生成一个链表
//并且原来Entry<k,v>中会有一个next指针，指向新的Entry<k,v>。这样一个table[index]或者bucket，会有2个Entry<k,v>。


//HashMap:put方法逻辑
//(1)如果HashMap未被初始化，则初始化(resize()初始化生成新HashMap)。
//(2)使用put的Entry<k,v>中的key求hash值，然后计算数组下标table[index](index=hash(key))，如果不存在table[index]就newNode一个。
//(3)如果没有碰撞，直接放入bucket中。
//(4)如果碰撞了，以链表的方式连接到Entry<k,v>后面(自带指针)。
//(5)如果链表长度超过阈值(8),就会把链表转成红黑树。
//(6)如果链表长度低于6,就把红黑树转回链表。
//(7)如果节点key已经存在，就替换旧值Entry<k,y>。
//(8)如果散列表容量满了(定义的数组容量,默认容量16*负载因子0.75),就resize(扩容2倍后重排)。

//HashMap:get方法逻辑
//(1)通过key求hash值，找到对应的table[index] bucket,键对象的equals()方法找到正确的Entry<k,v>。
//(2)如果对应bucket是一个链表，则先获取第一个对象的key进行比较，然后通过对象.next获取下一个。

//HashMap：扩容过程(resize())
//1.判断数组容量是否大于散列表容量比例(例如，默认容量16*负载因子0.75)
//2.如果是大于则，重新生成数组，容量为以前的2倍(如果是16，则新数组为32)，并且根据key重新计算每个table的index。
//非常消耗内存

//如何减少碰撞
//使用final对象：final对象是无法改变的，防止因key-value改变而导致的哈希值修改碰撞。

//HashMap, HashTable,CurrentHaspMap区别
//HashTable,线程安全，因为每个方法都添加了synchronized，内部结构是数组+链表，其他都跟HashMap一致。
//HashMap，线程不安全，内部结构是数组+链表+红黑树。
//CurrentHaspMap,线程安全，Cas+同步锁，数组+链表+红黑树。(进行读写元素操作时候，会对数组里面的对象添加同步锁)