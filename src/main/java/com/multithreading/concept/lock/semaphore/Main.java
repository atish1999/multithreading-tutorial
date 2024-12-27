package com.multithreading.concept.lock.semaphore;

import java.util.concurrent.Semaphore;

public class Main {

  public static void main(String[] args) {
    Semaphore semaphore = new Semaphore(2);
    SharedResource resource = new SharedResource(semaphore);
    Thread t1 = new Thread(resource::read, "T1");
    Thread t2 = new Thread(resource::read, "T2");
    Thread t3 = new Thread(resource::read, "T3");
    Thread t4 = new Thread(resource::read, "T4");

    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }
}
