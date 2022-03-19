package com.qilinger.justplay.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class ConcurrentService {

    public static TimeUnit unit = TimeUnit.MINUTES;
    public static BlockingQueue workQueue = new LinkedBlockingQueue<>();
    public static final Integer corePoolSize = 10;
    public static final Integer maximumPoolSize = 30;
    public static final Integer keepAliveTime = 60;
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

    public void poolHandler() {
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    }

    public String concurrentTest1() {
        threadPoolExecutor.submit(new mythread());
        return mythread.count.toString();
    }
    public static class mythread extends Thread{
        public static Integer count = 0;
        @Override
        public void run() {
            count++;
            System.out.println(count);

        }
    }
}
