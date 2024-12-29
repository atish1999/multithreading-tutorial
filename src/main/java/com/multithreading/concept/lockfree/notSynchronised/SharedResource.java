package com.multithreading.concept.lockfree.notSynchronised;

public class SharedResource {

  private int counter;

  public void increment() {
    counter++;
  }

  public int getCounter() {
    return counter;
  }
}
