package com.example.week04_mutilthread.homework;

public class threadB implements Runnable{
    @Override
    public void run() {
        System.out.println("线程B开始执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程B出现异常");
        }
        System.out.println("线程B执行结束");
    }
}
