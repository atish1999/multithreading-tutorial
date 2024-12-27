package com.multithreading.concept.lock.readwritre;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

  public static void main(String[] args) {

    ReadWriteLock lock = new ReentrantReadWriteLock();
    SharedResource resource = new SharedResource();

    Thread t1 =
        new Thread(
            () -> {
              resource.read(lock);
            },
            "Thread: T1");

    Thread t2 =
        new Thread(
            () -> {
              resource.read(lock);
            },
            "Thread: T2");

    Thread t3 =
        new Thread(
            () -> {
              resource.write(lock);
            },
            "Thread: T3");

    t1.start();
    t2.start();
    t3.start();
  }
}
