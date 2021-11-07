package com.example.week01_jvm01.classloadernew;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader{
    public static void main(String[] args) throws Exception {
        // 相关参数
        final String className = "Hello";
        final String methodName = "hello";
        // 创建类加载器
        ClassLoader classLoader = new XlassLoader();
        // 加载相应的类
        Class<?> clazz = classLoader.loadClass(className);
        // 看看里面有些什么方法
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName()+"."+m.getName());
        }
        //创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        //调用实例方法
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //如果支持包名，则需要路径转换
        String resourcePath = name.replace("." ,"/");
        //文件后缀
        final String stuffix = ".xlass";
        //获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath+stuffix);
        try {
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            //转换
            byte[] classBytes = decode(byteArray);
            //通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //解码
    private static byte[] decode(byte[] byteArray) {
        byte[] targerArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targerArray[i] = (byte)(255-byteArray[i]);
        }
        return targerArray;
    }

}
