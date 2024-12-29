package com.multithreading.concept.lockfree.synchronised;

public class SharedResource {

  private int counter;

  public synchronized void increment() {
    counter++;
  }

  public int getCounter() {
    return counter;
  }
}
