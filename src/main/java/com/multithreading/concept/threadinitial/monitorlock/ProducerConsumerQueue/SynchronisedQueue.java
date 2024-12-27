package com.multithreading.concept.threadinitial.monitorlock.ProducerConsumerQueue;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.sleep;
import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import java.util.Queue;

public class SynchronisedQueue<U> {

  private final Queue<U> synchronizedQueue;
  private final int capacity;

  public SynchronisedQueue(Queue<U> synchronizedQueue, int capacity) {
    this.synchronizedQueue = synchronizedQueue;
    this.capacity = capacity;
  }

  public synchronized void addItem(U item) throws InterruptedException {

    System.out.printf("%s is trying to produce item: %s %n", threadName(), item);
    while (queueIsFull()) {
      System.out.printf(
          "Queue is full, %s is going to waiting state and waiting for consumer to consume %n",
          threadName());
      wait();
      System.out.printf(
          "%s is waking up from the wait. current queue: %s%n", threadName(), synchronizedQueue);
    }

    sleep(4);
    synchronizedQueue.add(item);
    System.out.printf(
        "%s has produced item: %s, notifying consumer about this%n", threadName(), item);
    notify();
  }

  public synchronized void removeItem() throws InterruptedException {

    System.out.printf("%s is trying to consume item from: %s %n", threadName(), synchronizedQueue);
    while (synchronizedQueue.isEmpty()) {
      System.out.printf(
          "As queue is empty %s has to wait until there is any item %n", threadName());
      wait();
      System.out.printf(
          "%s is waking up from the wait. current queue: %s %n", threadName(), synchronizedQueue);
    }

    sleep(3);
    U consumedItem = synchronizedQueue.poll();
    System.out.printf(
        "%s has consumed item: %s, notifying producer about this %n", threadName(), consumedItem);
    notify();
  }

  private boolean queueIsFull() {
    return synchronizedQueue.size() == capacity;
  }
}
