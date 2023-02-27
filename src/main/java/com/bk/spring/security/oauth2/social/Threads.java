package com.bk.spring.security.oauth2.social;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import java.util.stream.IntStream;

public class Threads {
    //AtomicInteger count  = new AtomicInteger(0);
    int count = 0 ;
    long timetaken = 0;
    boolean threadrunning = true;


    /*
    Runnable task = () -> {

        //System.out.println("Hello from the thread " + this.count + " time(s) by thread: " + Thread.currentThread().getName());
    };
    */


    public static void main(String[] args) {
        Threads threads = new Threads();
        threads.runThreads();
        System.out.println("\nTime Taken: " + threads.timetaken);
        System.out.println("\nCount: " + threads.count);
    }

    public void runThreads()
    {
        ExecutorService monitoringService = Executors.newFixedThreadPool(1);
        ExecutorService executorService = Executors.newWorkStealingPool();
        long startTime  = System.currentTimeMillis();
        Runnable task = () -> {
            int i= 0;
            while(executorService.isTerminated() == false)
            {
                System.out.print("->");++i;if(i== 100){System.out.println();i=0;}
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (Exception e) {}
            }
        };

        monitoringService.submit(task);
        IntStream.range(0,1000000).forEach(i -> executorService.submit(() -> {
            this.increment();

        }));



        shutdown(executorService);
        shutdown(monitoringService);
        long endTime  = System.currentTimeMillis();
        timetaken = endTime - startTime;
    }

    synchronized   void increment()
    {
        this.count = this.count + 1;
    }

    public void shutdown(ExecutorService executorService)
    {
        try {
            //System.out.println("\nattempt to shutdown executor");
            executorService.shutdown();
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("\ntasks interrupted");
        }
        finally {
            if (!executorService.isTerminated()) {
                System.err.println("\ncancel non-finished tasks");
            }
            executorService.shutdownNow();
            threadrunning = false;
            //System.out.println("\nshutdown finished");
        }
    }

}
