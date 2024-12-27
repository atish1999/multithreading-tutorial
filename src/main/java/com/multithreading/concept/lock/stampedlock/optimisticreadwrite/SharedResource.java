package com.multithreading.concept.lock.stampedlock.optimisticreadwrite;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.concept.threadinitial.util.PrinterUtil;
import com.multithreading.concept.threadinitial.util.ThreadUtil;
import java.util.concurrent.locks.StampedLock;

public class SharedResource {

  private final StampedLock lock;
  private final PrinterUtil printerUtil;

  int data = 10;

  public SharedResource(StampedLock lock) {
    this.lock = lock;
    this.printerUtil = new PrinterUtil();
  }

  public void read() {

    long stamp = lock.tryOptimisticRead();
    try {
      printerUtil.print("Read lock is acquired by: {0}", threadName());
      data = 11;
      ThreadUtil.sleep(8);

      if (lock.validate(stamp)) {
        printerUtil.print("data is updated successfully");
      } else {
        printerUtil.print("rolling back to previous version");
      }

    } catch (Exception ignored) {
    }
  }

  public void readPessimistic() {

    long stamp = lock.readLock();
    try {
      printerUtil.print("Read lock is acquired by: {0}", threadName());
      data = 1;
      ThreadUtil.sleep(4);

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
      data = 20;
      ThreadUtil.sleep(2);
    } catch (Exception ignored) {
    } finally {
      printerUtil.print("Write lock is released by: {0}", threadName());
      lock.unlockWrite(stamp);
    }
  }

  public void writeWithNoChange() {
    long stamp = lock.writeLock();
    try {
      printerUtil.print("Write lock is acquired by: {0}", threadName());
      ThreadUtil.sleep(2);
    } catch (Exception ignored) {
    } finally {
      printerUtil.print("Write lock is released by: {0}", threadName());
      lock.unlockWrite(stamp);
    }
  }
}
