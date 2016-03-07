package com.web.clazz;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by erbao.wang on 2016/3/7.
 *
 * @desc
 */
public class MyClassLoader extends  ClassLoader {



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String path="E:/demo/hello-annotation/bin/com/web/bit/";
        String path="C:/";
//        String classPath = path + name + ".class";
        String classPath = path + name + ".zip";
        System.out.println(classPath);
        InputStream is = null;
        ByteArrayOutputStream os = null;
        try {
            is = new FileInputStream(new File(classPath));
            os = new ByteArrayOutputStream();
            int b = 0;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
            byte[] bytes = os.toByteArray();
            os.flush();
            return defineClass(null, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(os!=null)
                    os.close();
                if(is!=null)
                    is.close();
            } catch (IOException e1) {
                os=null;
                is = null;
            }
        }
        return null;
    }



    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader=new MyClassLoader();
        Class clazz= myClassLoader.findClass("BitDemo");
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("main", new Class[]{String[].class});
        method.invoke((Object) null, new Object[]{new String[0]});

        System.out.println(clazz.getClassLoader());


        Class clazz1= Class.forName("java.lang.String");

        System.out.println(clazz1.getClassLoader());

    }
}
