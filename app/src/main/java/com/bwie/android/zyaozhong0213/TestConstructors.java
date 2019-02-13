package com.bwie.android.zyaozhong0213;

import java.lang.reflect.Constructor;

public class TestConstructors {

    private static Object o;

    public static void main(String[] args) throws Exception {
        //1.加载Class对象

        Class clazz = Class.forName("com.bwie.android.zyaozhong0213.bean.Student");
//        获取所有公有的构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
//1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。
        Constructor constructor = clazz.getConstructor(null);
        System.out.println("公有、无参的构造方法" + constructor);

        o = constructor.newInstance();
        System.out.println("******************获取私有构造方法，并调用*******************************");
        Constructor constructor1 = clazz.getDeclaredConstructor(int.class);
        System.out.println(constructor1);
        //调用构造方法
        constructor1.setAccessible(true);
         constructor1.newInstance(10);

    }


}
