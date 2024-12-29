package com.multithreading.concept.threadpool.future;

import static com.multithreading.util.ThreadUtil.threadName;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BasicFuture {

  public static void main(String[] args) {

    ThreadPoolExecutor executor =
        new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    // here the task will be executed by the threads which will be created in above thread pool
    Future<?> future =
        executor.submit(
            () -> {
              System.out.printf("task is executed by: %s%n", threadName());
            });

    // Main thread will continue processing

    // Caller (Main thread is checking the status of thread which was executing the above task
    System.out.println("TASK-STATUS: " + future.isDone());

    // if we are not shutting down the executor it will be alive
    executor.shutdown();
  }
}
