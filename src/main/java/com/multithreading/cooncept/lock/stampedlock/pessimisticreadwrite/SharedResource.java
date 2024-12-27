package com.multithreading.cooncept.lock.stampedlock.pessimisticreadwrite;

import static com.multithreading.cooncept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.cooncept.threadinitial.util.PrinterUtil;
import com.multithreading.cooncept.threadinitial.util.ThreadUtil;
import java.util.concurrent.locks.StampedLock;

public class SharedResource {

  boolean itemAvailable = false;
  private final StampedLock lock;
  private final PrinterUtil printerUtil;

  public SharedResource(StampedLock lock) {
    this.lock = lock;
    this.printerUtil = new PrinterUtil();
  }

  public void read() {

    long stamp = lock.readLock();
    try {
      printerUtil.print("Read lock is acquired by: {0}", threadName());
      ThreadUtil.sleep(8);
    } catch (Exception ignored) {
    } finally {
      printerUtil.print("Read lock is released by: {0}", threadName());
      lock.unlockRead(stamp);
    }
  }

  public void write() {
    long stamp = lock.writeLock();
    try {
      printerUtil.print("Write lock is acquired by: {0}", threadName());
      ThreadUtil.sleep(8);
      itemAvailable = true;
    } catch (Exception ignored) {
    } finally {
      printerUtil.print("Write lock is released by: {0}", threadName());
      lock.unlockWrite(stamp);
    }
  }
}
