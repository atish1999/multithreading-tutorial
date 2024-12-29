package com.multithreading.concept.threadpool.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureGetMethod {

  private final ThreadPoolExecutor executor;

  public FutureGetMethod() {
    this.executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    ;
  }

  public ThreadPoolExecutor getExecutor() {
    return executor;
  }
}
