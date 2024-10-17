package com.yss;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyConcurrentQueue<T> {
    private Object[] arr;

    private int s;
    private int e;

    private int size;

    private ReentrantLock lock = new ReentrantLock();

    public MyConcurrentQueue() {
        arr = new Object[10];
        s = 0;
        e = 0;
        size = 0;
    }

    public MyConcurrentQueue(int cap) {
        arr = new Object[cap];
        s = 0;
        e = 0;
        size = 0;
    }

    public synchronized void add(T num) {
        if (size == arr.length) {
            return;
        }
        arr[e] = num;
        e = (e + 1) % (arr.length);
        ++size;
    }

    public synchronized T peek() {
        return (T) arr[s];
    }

    public synchronized T poll() {
        T temp = (T) arr[s];
        s = (s + 1) % arr.length;
        --size;
        return temp;
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }


}
