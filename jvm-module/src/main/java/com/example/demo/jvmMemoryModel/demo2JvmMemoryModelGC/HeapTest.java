package com.example.demo.jvmMemoryModel.demo2JvmMemoryModelGC;

import java.util.ArrayList;

/**
 * @Description:
 * GC 回收实战
 * @Author HeSuiJin
 * @Date 2021/4/9
 */
public class HeapTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Heap> heapArrayList = new ArrayList<>();
        while (true){
            //不断的new 一个对象
            heapArrayList.add(new Heap());
            Thread.sleep(100);
        }
    }

}
