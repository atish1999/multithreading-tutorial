package com.multithreading.cooncept.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
  private ReentrantLock lock = new ReentrantLock();

  public void testReentrantLock() {
    Thread t1 =
        new Thread(
            () -> {
              lock.lock();
              System.out.println(
                  "Thread " + Thread.currentThread().getName() + " has acquired the lock");
              System.out.println("hold count " + lock.getHoldCount());
              lock.lock(); // Acquire the lock again
              System.out.println(
                  "Thread " + Thread.currentThread().getName() + " has acquired the lock again");
              System.out.println("hold count " + lock.getHoldCount());
              lock.unlock();
              lock.unlock(); // Release the lock twice
            },
            "Thread-1");

    t1.start();
  }

  public static void main(String[] args) {
    ReentrantLockTest test = new ReentrantLockTest();
    test.testReentrantLock();
  }
}
