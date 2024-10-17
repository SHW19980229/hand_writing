package com.yss;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintDemo {

    private ReentrantLock lock = new ReentrantLock();
    private Condition con = lock.newCondition();

    private int  cnt = 1;

    public void show(int num) {
        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<num;++i){
                    lock.lock();
                    try{
                        while(cnt!=1){
                            con.await();
                        }
                        System.out.print("A");
                        cnt=2;
                        con.signalAll();
                    }catch(InterruptedException exception){
                        exception.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<num;++i){
                    lock.lock();
                    try{
                        while(cnt!=2){
                            con.await();
                        }
                        System.out.print("B");
                        cnt=3;
                        con.signalAll();
                    }catch(InterruptedException exception){
                        exception.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        });
        Thread threadC=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<num;++i){
                    lock.lock();
                    try{
                        while(cnt!=3){
                            con.await();
                        }
                        System.out.print("C");
                        cnt=1;
                        con.signalAll();
                    }catch(InterruptedException exception){
                        exception.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            }
        });
        cnt=1;
        threadA.start();
        threadB.start();
        threadC.start();
    }

}
