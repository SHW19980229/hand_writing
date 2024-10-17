package com.yss;

public class MyQueue<T> {
    private Object[] arr;

    private int s;
    private int e;

    private int size;

    public MyQueue() {
        arr = new Object[10];
        s = 0;
        e = 0;
        size = 0;
    }

    public MyQueue(int cap) {
        arr = new Object[cap];
        s = 0;
        e = 0;
        size = 0;
    }

    public void add(T num) {
        if (size == arr.length) {
            return;
        }
        arr[e] = num;
        e = (e + 1) % (arr.length);
        ++size;
    }

    public T peek() {
        return (T) arr[s];
    }

    public T poll() {
        if(size==0) return null;
        T temp = (T) arr[s];
        s = (s + 1) % arr.length;
        --size;
        return temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}
