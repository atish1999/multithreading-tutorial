package com.multithreading.concept.lock.readwritre;

import static com.multithreading.concept.threadinitial.util.ThreadUtil.threadName;

import com.multithreading.concept.threadinitial.util.PrinterUtil;
import com.multithreading.concept.threadinitial.util.ThreadUtil;
import java.util.concurrent.locks.ReadWriteLock;

public class SharedResource {

  boolean isItemAvailable;
  private final PrinterUtil printerUtil;

  public SharedResource() {
    this.printerUtil = new PrinterUtil();
  }

  public void read(ReadWriteLock lock) {

    try {
      lock.readLock().lock();
      printerUtil.print("Read lock is acquired by: {0}", threadName());
      ThreadUtil.sleep(8);

    } catch (Exception ignored) {

    } finally {
      printerUtil.print("Read lock is released by: {0}", threadName());
      lock.readLock().unlock();
    }
  }

  public void write(ReadWriteLock lock) {

    try {
      lock.writeLock().lock();
      printerUtil.print("Write lock is acquired by: {0}", threadName());
      isItemAvailable ^= true;
      ThreadUtil.sleep(4);

    } catch (Exception ignored) {

    } finally {
      printerUtil.print("Write lock is released by: {0}", threadName());
      lock.writeLock().unlock();
    }
  }
}
