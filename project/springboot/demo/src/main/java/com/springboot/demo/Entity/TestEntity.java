package com.springboot.demo.Entity;


import java.util.Comparator;
import java.util.Objects;

public class TestEntity implements Comparator<TestEntity>,Comparable<TestEntity> {
    private String id;
    private String name;

    public TestEntity(){
    }

    public TestEntity(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //Comparator的实现方法，用于两个对象之间的某个属性比较
    @Override
    public int compare(TestEntity o1, TestEntity o2) {
        return o1.getName().compareTo(o2.getName()); //等于0,值相等；大于0，o1>o2；小于0,o1<o2;
    }

    @Override
    //Comparable的实现方法，
    public int compareTo(TestEntity o) {
        return this.id.compareTo(o.getId());
    }

}
