package com.yss;


import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
   public void TestMap(){
       MyHashMap<Integer,Integer> map=new MyHashMap<>();
       for(int i=0;i<128;++i){
           map.put(i,i);
       }
        for(int i=0;i<128;++i){
            map.remove(i);
        }
        for(int i=0;i<128;++i){
            map.put(i,i);
        }
        for(int i=0;i<150;++i){
            Integer num=map.get(i);
            if(num==null)
                System.out.println("null");
            else
                System.out.println(num);
        }
   }


}
