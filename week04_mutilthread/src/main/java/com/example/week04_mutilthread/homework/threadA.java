package com.example.week04_mutilthread.homework;

public class threadA implements Runnable{
    @Override
    public void run() {
        System.out.println("线程A开始执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程A出现异常");
        }
        System.out.println("线程A执行结束");
    }
}
