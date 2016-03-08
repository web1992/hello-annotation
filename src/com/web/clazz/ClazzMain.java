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

        // doSomeThing �����ǹ��õģ��Ǿ�̬��������Ҫʵ��ȥ����
        Method methodDoSomeThing = clazz.getMethod("doSomeThing", null);

        //ʵ������,��Ҫ���ʵ�����󣬵�һ����������Ϊ��
        methodDoSomeThing.invoke(obj, null);


        Method methodMain = clazz.getMethod("main", new Class[]{String[].class});
        // ��̬����,����Ҫ���ʵ�����󣬵�һ��������Ϊ��
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
