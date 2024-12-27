package com.multithreading.concept.lock.synchronised;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.concept.threadinitial.util.PrinterUtil;
import com.multithreading.concept.threadinitial.util.ThreadUtil;

public class SharedResource {

  boolean isItemAvailable = false;
  private final PrinterUtil printerUtil;

  public SharedResource() {
    this.printerUtil = new PrinterUtil();
  }

  public synchronized void produce() {

    try {
      printerUtil.print("lock is acquired by: {0}", threadName());
      isItemAvailable ^= true;
      ThreadUtil.sleep(4);
      printerUtil.print("isItemAvailable value: {0}", isItemAvailable);
    } catch (Exception ignored) {

    }

    printerUtil.print("lock is released by: {0}", threadName());
  }
}
