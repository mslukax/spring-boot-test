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

//Map集合概念

//1.HashMap概念以及工作原理
//HashMap由数组(默认16个数组)+链表+红黑树(链表太多时候使用)组成，非同步(线程不安全)
