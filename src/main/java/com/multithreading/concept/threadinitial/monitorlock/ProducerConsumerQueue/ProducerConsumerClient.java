package com.multithreading.concept.threadinitial.monitorlock.ProducerConsumerQueue;

import java.util.ArrayDeque;

public class ProducerConsumerClient {

  public static void main(String[] args) {

    SynchronisedQueue<Integer> synchronisedQueue = new SynchronisedQueue<>(new ArrayDeque<>(), 5);

    final Thread producer =
        new Thread(
            () -> {
              for (int item = 1; item <= 10; ++item) {
                try {
                  synchronisedQueue.addItem(item);
                } catch (InterruptedException e) {
                  System.err.printf(
                      "InterruptedException occurred while trying to produce item: %s, reason: %s%n",
                      item, e.getMessage());
                }
              }
            },
            "Producer");

    final Thread consumer =
        new Thread(
            () -> {
              for (int item = 1; item <= 10; ++item) {
                try {
                  synchronisedQueue.removeItem();
                } catch (InterruptedException e) {
                  System.err.printf(
                      "InterruptedException occurred while trying to produce item: %s, reason: %s%n",
                      item, e.getMessage());
                }
              }
            },
            "Consumer");

    consumer.start();
    producer.start();

    System.out.println("Main thread has completed execution");
  }
}
