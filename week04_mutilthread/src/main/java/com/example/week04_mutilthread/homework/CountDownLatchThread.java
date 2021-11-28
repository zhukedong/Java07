package com.example.week04_mutilthread.homework;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchThread implements Runnable{
    private String threadName;
    private CountDownLatch latch;

    public CountDownLatchThread(String threadName, CountDownLatch latch) {
        this.threadName = threadName;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("线程"+threadName+"开始执行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+threadName+"执行完成");
        latch.countDown();
    }

    public static void main(String[] args) {
        Thread threadX = new Thread(new Runnable() {
            @Override
            public void run() {
                CountDownLatch latch = new CountDownLatch(2);
                Thread threadA = new Thread(new CountDownLatchThread("A",latch));
                Thread threadB = new Thread(new CountDownLatchThread("B",latch));
                threadA.start();
                threadB.start();
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("主线程执行完成");
            }
        });
        threadX.start();
    }
}
