package com.multithreading.cooncept.threadinitial.monitorlock.basicmonitorLock;

public class Main {

  public static void main(String[] args) {

    MonitorLock monitorLock = new MonitorLock();

    //    Thread t1 = new Thread(monitorLock::task1, "t1");
    // Alternative of above
    Thread t1 = new Thread(new MonitorLockRunnable(monitorLock), "t1");

    Thread t2 = new Thread(monitorLock::task2, "t2");
    Thread t3 = new Thread(monitorLock::task3, "t3");

    t1.start();
    t2.start();
    t3.start();
  }

  static class MonitorLockRunnable implements Runnable {

    private final MonitorLock monitorLock;

    public MonitorLockRunnable(MonitorLock monitorLock) {
      this.monitorLock = monitorLock;
    }

    @Override
    public void run() {
      monitorLock.task1();
    }
  }
}
