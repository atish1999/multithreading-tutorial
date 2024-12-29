package com.multithreading.concept.lockfree.atomicLockFree;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {

  private final AtomicInteger counter = new AtomicInteger(0);

  public void increment() {
    counter.incrementAndGet();
  }

  public int getCounter() {
    return counter.get();
  }
}
