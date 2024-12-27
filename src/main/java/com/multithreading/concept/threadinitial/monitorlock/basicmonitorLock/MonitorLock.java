package com.multithreading.concept.threadinitial.monitorlock.basicmonitorLock;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.sleep;
import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;
import static java.text.MessageFormat.format;

public class MonitorLock {

  public synchronized void task1() {

    System.out.println(format("Inside Task1: {0}", threadName()));

    try {
      sleep(10);
    } catch (Exception exception) {

    }
  }

  public void task2() {

    System.out.println(format("Inside task2 before synchronized by: {0}", threadName()));

    synchronized (this) {
      System.out.println(format("Inside Task2: {0}", threadName()));
    }
  }

  public void task3() {
    System.out.println(format("Inside Task3: {0}", threadName()));
  }
}
