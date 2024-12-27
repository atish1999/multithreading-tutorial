package com.multithreading.concept.threadinitial.util;

import static java.text.MessageFormat.format;

import java.util.concurrent.TimeUnit;

public class ThreadUtil {

  public static String threadName() {
    return Thread.currentThread().getName();
  }

  public static void sleep(int count) {
    try {
      Thread.sleep(TimeUnit.SECONDS.toMillis(count));
    } catch (InterruptedException e) {
      System.err.println(format("exception occurred while trying to sleep in :{0}", threadName()));
    }
  }
}
