package com.multithreading.concept.threadinitial.monitorlock.sharedResource;

import com.multithreading.util.PrinterUtil;

public class Client {
  public static void main(String[] args) {

    PrinterUtil printer = new PrinterUtil();
    SharedResource sharedResource = new SharedResource(printer);

    ProducerTask producerTask = new ProducerTask(sharedResource);
    ConsumerTask consumerTask = new ConsumerTask(sharedResource);

    Thread producer = new Thread(producerTask, "Producer");
    Thread consumer = new Thread(consumerTask, "Consumer");

    consumer.start();
    producer.start();
  }
}
