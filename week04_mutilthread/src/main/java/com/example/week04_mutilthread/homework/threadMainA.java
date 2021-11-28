package com.example.week04_mutilthread.homework;

/***
 * 方法A：通过join实现
 */

public class threadMainA {
    public static void main(String[] args) {
        Thread threadMainA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("主线程开始执行");
                Thread threadA = new Thread(new threadA());
                Thread threadB = new Thread(new threadB());
                threadA.start();
                threadB.start();
                try {
                    threadA.join();
                    threadB.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("主线程执行异常");
                }
                System.out.println("主线程执行结束");
            }
        });
        threadMainA.start();
    }
}
