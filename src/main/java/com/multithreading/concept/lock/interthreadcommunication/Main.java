package com.multithreading.concept.lock.interthreadcommunication;

public class Main {

  public static void main(String[] args) {

    SharedResource sharedResource = new SharedResource();
    Thread producer =
        new Thread(
            () -> {
              for (int i = 0; i < 5; ++i) {
                sharedResource.produce();
                System.out.println("\n------------------------------------------\n");
              }
            },
            "PRODUCER | ");

    Thread consumer =
        new Thread(
            () -> {
              for (int i = 0; i < 5; ++i) {
                sharedResource.consume();
                System.out.println("\n------------------------------------------\n");
              }
            },
            "CONSUMER | ");

    consumer.start();
    producer.start();
  }
}
