package com.example.week04_mutilthread.test;

import com.example.week04_mutilthread.homework.threadA;
import com.example.week04_mutilthread.homework.threadB;

public class threadMainB {
    public static void main(String[] args) {
        Thread threadMainB = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread threadA = new Thread(new threadA());
                Thread threadB = new Thread(new threadB());
                threadA.start();
                threadB.start();
            }
        });
        threadMainB.start();
        System.out.println("主线程开始执行");
        try {
            System.out.println("主线程进入等待");
            synchronized (threadMainB){
                threadMainB.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (threadMainB){
            threadMainB.notify();
        }
        System.out.println("主线程执行结束");
    }
}
