package com.multithreading.concept.lock.semaphore;

import static com.multithreading.util.ThreadUtil.threadName;

import com.multithreading.util.PrinterUtil;
import com.multithreading.util.ThreadUtil;
import java.util.concurrent.Semaphore;

public class SharedResource {

  private final Semaphore lock;
  private final PrinterUtil printerUtil;

  public SharedResource(Semaphore semaphore) {
    this.lock = semaphore;
    this.printerUtil = new PrinterUtil();
  }

  public void read() {

    try {
      lock.acquire();
      printerUtil.print("Read lock is acquired by: {0}", threadName());
      ThreadUtil.sleep(4);
    } catch (Exception ignored) {
    } finally {
      printerUtil.print("Read lock is released by: {0}", threadName());
      lock.release();
    }
  }
}
