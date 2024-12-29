package com.multithreading.concept.threadpool.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureExampleWithRunnableCallable {

  public static void main(String[] args) {

    ThreadPoolExecutor poolExecutor =
        new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

    // Use case-1
    Future<?> futureObj1 = poolExecutor.submit(() -> System.out.println("Task-1 with runnable"));
    try {
      Object result = futureObj1.get();
      System.out.println(result == null);
    } catch (Exception ignored) {

    }

    // Use case-2 [ workaround to get the result ]
    List<Integer> arrayList = new ArrayList<>();
    Future<List<Integer>> futureObj2 =
        poolExecutor.submit(
            () -> {
              System.out.println("Task-2 with runnable but with a result");
              arrayList.add(1);
            },
            arrayList);

    try {
      List<Integer> integers = futureObj2.get();
      System.out.println("integers = " + integers);
    } catch (Exception ignored) {
    }

    // Use case-3 [ cleaner way ]
    Future<List<Integer>> futureObjWithCallable =
        poolExecutor.submit(
            () -> {
              System.out.println("task with callable");
              return Arrays.asList(1, 2);
            });

    try {
      List<Integer> result = futureObjWithCallable.get();
      System.out.println("result from callable = " + result);
    } catch (Exception ignored) {

    }

    poolExecutor.shutdown();
  }
}
