package com.bwie.android.zyaozhong0213;

import com.bwie.android.zyaozhong0213.bean.Student;

import java.lang.reflect.Field;

public class TestField {
    public static void main(String[] args) throws Exception {

        //1.获取Class对象
        Class<?> aClass = Class.forName("com.bwie.android.zyaozhong0213.bean.Student");
        //获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fields = aClass.getFields();
        if (fields != null && fields.length > 0) {
            for (Field f : fields) {
                System.out.println(f);
            }
        }

        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] declaredFields = aClass.getDeclaredFields();
        if (declaredFields!=null&&declaredFields.length>0){
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField);
            }
        }

        System.out.println("*************获取公有字段**并调用***********************************");
        Field name = aClass.getField("name");
        System.out.println(name);

        //获取一个对象
        Object o = aClass.getConstructor().newInstance();

        //为字段设置值
//        第一个参数：要传入设置的对象，第二个参数：要传入实参
        name.set(o,"张三");
        //验证
        Student student = (Student) o;
        System.out.println("验证姓名："+student.name);

        System.out.println("**************获取私有字段****并调用********************************");
        Field phoneNum = aClass.getDeclaredField("phoneNum");
        System.out.println(phoneNum);
        phoneNum.setAccessible(true);
        phoneNum.set(o,"18311122498");
        System.out.println("验证电话：" + student);


    }
}
