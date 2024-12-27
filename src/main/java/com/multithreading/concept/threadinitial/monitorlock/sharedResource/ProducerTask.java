package com.multithreading.concept.threadinitial.monitorlock.sharedResource;

public class ProducerTask implements Runnable {

  final SharedResource sharedResource;

  public ProducerTask(SharedResource sharedResource) {
    this.sharedResource = sharedResource;
  }

  @Override
  public void run() {

    try {
      for (int i = 0; i < 10; ++i) {
        sharedResource.produce();
      }
    } catch (Exception exception) {

    }
  }
}
