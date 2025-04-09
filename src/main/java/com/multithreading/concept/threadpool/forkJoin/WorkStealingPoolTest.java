package com.multithreading.concept.threadpool.forkJoin;

import java.util.concurrent.*;

public class WorkStealingPoolTest {

  public static void main(String[] args) {

    extracted();
  }

  private static void extracted1() {
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    ForkJoinTask<Integer> submit = forkJoinPool.submit(new ComputeSumTask(1, 10));

    try {
      System.out.println(submit.get());
    } catch (Exception exception) {

    }
  }

  private static void extracted() {
    ExecutorService executorService = Executors.newWorkStealingPool();

    ForkJoinPool executorService1 = (ForkJoinPool) executorService;
    Integer submit1 = executorService1.invoke(new ComputeSumTask(0, 10));

    try {
      System.out.println("sum = " + submit1);
    } catch (Exception ignored) {
    }
  }

}
