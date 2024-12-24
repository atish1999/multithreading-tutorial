package com.multithreading.cooncept.threadinitial.monitorlock.sharredResource;

public class ConsumerTask implements Runnable {
  final SharedResource sharedResource;

  public ConsumerTask(final SharedResource sharedResource) {
    this.sharedResource = sharedResource;
  }

  @Override
  public void run() {

    try {
      for (int i = 0; i < 10; ++i) {
        sharedResource.consume();
      }
    } catch (Exception exception) {

    }
  }
}