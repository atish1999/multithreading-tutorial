package com.multithreading.cooncept.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

  public static void main(String[] args) {

    ReentrantLock lock = new ReentrantLock();

    SharedResource sharedResource1 = new SharedResource();
    SharedResource sharedResource2 = new SharedResource();

    Thread thread1 =
        new Thread(
            () -> {
              sharedResource1.produce(lock);
            },
            "Thread - 1");

    Thread thread2 =
        new Thread(
            () -> {
              sharedResource2.produce(lock);
            },
            "Thread - 2");

    thread1.start();
    thread2.start();
  }
}
