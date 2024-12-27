package com.multithreading.concept.lock.interthreadcommunication;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.concept.threadinitial.util.PrinterUtil;
import com.multithreading.concept.threadinitial.util.ThreadUtil;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

  boolean itemAvailable = false;
  ReentrantLock lock = new ReentrantLock();
  Condition condition = lock.newCondition();
  PrinterUtil printerUtil = new PrinterUtil();

  public void produce() {

    try {
      lock.lock();
      printerUtil.print("Lock is acquired by: {0}", threadName());
      while (itemAvailable) {
        printerUtil.print(
            "{0} is going to a waiting state as item is already produced", threadName());
        condition.await();
        printerUtil.print(
            "{0} is waking up from a waiting state. and itemAvailable = {1}",
            threadName(), itemAvailable);
      }

      ThreadUtil.sleep(4);
      itemAvailable = true;
      condition.signal();
      printerUtil.print("{0} has produced item, notified other threads to consume", threadName());

    } catch (Exception exception) {

    } finally {
      printerUtil.print("Lock is released by: {0}", threadName());
      lock.unlock();
    }
  }

  public void consume() {

    try {
      lock.lock();
      printerUtil.print("Lock is acquired by: {0}", threadName());
      while (!itemAvailable) {
        printerUtil.print("{0} is going to a waiting state as no item is there", threadName());
        condition.await();
        printerUtil.print(
            "{0} is waking up from a waiting state. and itemAvailable = {1}",
            threadName(), itemAvailable);
      }

      ThreadUtil.sleep(2);
      itemAvailable = false;
      condition.signal();
      printerUtil.print("{0} has consumed item, notified other threads to produce", threadName());

    } catch (Exception exception) {

    } finally {
      printerUtil.print("Lock is released by: {0}", threadName());
      lock.unlock();
    }
  }
}
