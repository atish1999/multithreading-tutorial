package com.multithreading.concept.lockfree.synchronised;

public class Main {

  public static void main(String[] args) {

    SharedResource sharedResource = new SharedResource();

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 400; ++i) {
                sharedResource.increment();
              }
            },
            "T1");

    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < 400; ++i) {
                sharedResource.increment();
              }
            },
            "T2");

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException ignored) {

    }

    System.out.println("counter = " + sharedResource.getCounter());
  }
}
