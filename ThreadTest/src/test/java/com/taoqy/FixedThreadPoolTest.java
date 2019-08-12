package com.taoqy;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/3/8
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */

public class FixedThreadPoolTest {
    @Test
    public void createFixedThreadPoolTest()  {
        //创建一个拥有10个线程的线程池
        //同时这10个线程都是一直存在的
        //若线程池满了，这时如果有新的任务要执行，则要执行的任务会被拒绝执行
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0;i < 9999999; i++){
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName());

                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    @Test
    public void useCallableInThreadPool() throws InterruptedException {
        ArrayList<Callable<Integer>> callables = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures1 = executorService.invokeAll(callables);
    }
}
