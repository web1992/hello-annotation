package com.web.clazz;

import java.lang.reflect.Method;

/**
 * Created by erbao.wang on 2016/3/8.
 *
 * @desc
 */
public class ClazzMain {


    public static void main(String[] args) throws Exception {

        ClassLoader myClassLoader = Thread.currentThread().getContextClassLoader();

        Class clazz = myClassLoader.loadClass("com.web.clazz.Demo");
        Object obj = clazz.newInstance();

        // doSomeThing 方法是公用的，非静态方法，需要实例去调用
        Method methodDoSomeThing = clazz.getMethod("doSomeThing", null);

        //实例方法,需要类的实例对象，第一个参数不能为空
        methodDoSomeThing.invoke(obj, null);


        Method methodMain = clazz.getMethod("main", new Class[]{String[].class});
        // 静态方法,不需要类的实例对象，第一个参数可为空
        methodMain.invoke((Object) null, (Object) null);

    }
}


class Demo {

    public static void main(String[] args) {
        System.out.println("main...");
    }


    public void doSomeThing() {
        System.out.println("doSomeThing...");
    }
}
