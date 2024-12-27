package com.multithreading.concept.lock.reentrant;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.concept.threadinitial.util.PrinterUtil;
import com.multithreading.concept.threadinitial.util.ThreadUtil;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

  boolean isItemAvailable = false;
  private final PrinterUtil printerUtil;

  public SharedResource() {
    this.printerUtil = new PrinterUtil();
  }

  public void produce(ReentrantLock lock) {

    try {
      lock.lock();
      printerUtil.print("lock is acquired by: {0}", threadName());
      isItemAvailable ^= true;
      ThreadUtil.sleep(4);
      printerUtil.print("isItemAvailable value: {0}", isItemAvailable);
    } catch (Exception ignored) {

    } finally {
      ThreadUtil.sleep(2);
      printerUtil.print("lock is released by: {0}", threadName());
      lock.unlock();
    }
  }
}
