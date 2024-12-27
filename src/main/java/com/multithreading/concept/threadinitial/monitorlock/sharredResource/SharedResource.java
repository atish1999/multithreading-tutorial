package com.multithreading.concept.threadinitial.monitorlock.sharredResource;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.concept.threadinitial.util.PrinterUtil;
import com.multithreading.concept.threadinitial.util.ThreadUtil;

public class SharedResource {

  boolean itemAvailable = false;
  private final PrinterUtil printer;

  public SharedResource(final PrinterUtil printer) {
    this.printer = printer;
  }

  public synchronized void produce() throws InterruptedException {

    printer.print("{0} is trying to produce", threadName());
    // we can go with if condition , but we are adding while here
    // because thread can have "spurious wake up" due to interruption or system noise
    while (itemAvailable) {
      printer.print("{0} is going to waiting stage. as item is already produced", threadName());
      wait();
      printer.print("{0} is waking up from waiting stage.", threadName());
    }

    ThreadUtil.sleep(3);
    itemAvailable = true;
    printer.print("{0} has produced an item", threadName());
    notifyAll();
    printer.print("Notified consumer");
  }

  public synchronized void consume() throws InterruptedException {

    printer.print("{0} is trying to consume", threadName());

    while (!itemAvailable) {
      printer.print("{0} is going to waiting stage. as no item is produced", threadName());
      wait();
      printer.print("{0} is waking up from waiting stage.", threadName());
    }
    ThreadUtil.sleep(2);
    itemAvailable = false;
    printer.print("{0} has consumed an item", threadName());
    notifyAll();
    printer.print("Notified producer");
  }
}
