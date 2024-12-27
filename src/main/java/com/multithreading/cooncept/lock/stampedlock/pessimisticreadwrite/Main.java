package com.multithreading.cooncept.lock.stampedlock.pessimisticreadwrite;

import java.util.concurrent.locks.StampedLock;

public class Main {

  public static void main(String[] args) {

    StampedLock lock = new StampedLock();
    SharedResource resource = new SharedResource(lock);

    Thread t1 = new Thread(resource::read, "T1");
    Thread t2 = new Thread(resource::read, "T2");
    Thread t3 = new Thread(resource::write, "T3");

    t1.start();
    t2.start();
    t3.start();
  }
}
