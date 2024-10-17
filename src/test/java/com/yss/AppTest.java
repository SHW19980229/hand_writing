package com.yss;


import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void TestMap() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 128; ++i) {
            map.put(i, i);
        }
        for (int i = 0; i < 128; ++i) {
            map.remove(i);
        }
        for (int i = 0; i < 128; ++i) {
            map.put(i, i);
        }
        for (int i = 0; i < 150; ++i) {
            Integer num = map.get(i);
            if (num == null)
                System.out.println("null");
            else
                System.out.println(num);
        }
    }

    @Test
    public void TestMyQueue() {
        MyQueue<Integer> queue=new MyQueue<>(3);
        Integer num=queue.poll();
        if(num==null) System.out.println("null");
        queue.add(1);
        queue.add(2);
        queue.add(3);
        while(!queue.isEmpty())
            System.out.println(queue.poll());
        queue.add(4);
        queue.add(5);
        queue.add(6);
        while(!queue.isEmpty())
            System.out.println(queue.poll());
    }

    @Test
    public void PrintABC(){
        PrintDemo demo=new PrintDemo();
        demo.show(5);
    }


}
