package com.multithreading.cooncept.lock.synchronised;

public class Main {

  public static void main(String[] args) {

    SharedResource resource1 = new SharedResource();
    Thread thread1 = new Thread(resource1::produce, "Thread1");

    SharedResource resource2 = new SharedResource();
    Thread thread2 = new Thread(resource2::produce, "Thread2");

    thread1.start();
    thread2.start();
  }
}
