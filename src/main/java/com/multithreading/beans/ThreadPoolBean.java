package com.multithreading.beans;

import com.multithreading.util.ThreadUtil;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolBean {

  private static ThreadPoolExecutor poolExecutor;
  private static final ReentrantLock lock = new ReentrantLock();

  private ThreadPoolBean() {}

  public static ThreadPoolExecutor getPoolExecutor() {
    try {
      lock.lock();
      if (Objects.isNull(poolExecutor)) {
        poolExecutor =
            new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        System.out.println("PoolExecutor gets initialized");
      }
      return poolExecutor;
    } finally {
      lock.unlock();
      System.out.println("lock is released by: " + ThreadUtil.threadName());
    }
  }
}
