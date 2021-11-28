package com.example.week04_mutilthread.homework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierThread {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        Thread threadx = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i < 9; i++) {
                    int finalI = i;
                    Thread thread = new Thread(()->{
                        System.out.println("子线程"+ finalI +"等待执行");
                        try {
                            cyclicBarrier.await();
                            Thread.sleep(1000);
                            System.out.println("子线程"+finalI+"执行完成");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    });
                    thread.start();
                }
            }
        });
        threadx.start();
        System.out.println("主线程等待执行");
        cyclicBarrier.await();
        Thread.sleep(1000);
        System.out.println("主线程执行完毕!");
    }
}
