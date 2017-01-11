package com.example.changshuchao.bean;

/**
 * Created by changshuchao on 2017/1/9.
 */
public class Person {
    private int _id;
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int id, String name, int age) {
        _id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "_id="+this._id+",name="+this.name+",age"+this.age;
    }
}
