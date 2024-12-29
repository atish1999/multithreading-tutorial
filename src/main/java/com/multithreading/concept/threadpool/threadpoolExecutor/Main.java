package com.multithreading.concept.threadpool.threadpoolExecutor;

import static com.multithreading.util.ThreadUtil.sleep;
import static com.multithreading.util.ThreadUtil.threadName;

import com.multithreading.util.PrinterUtil;
import java.util.concurrent.*;

public class Main {

  static PrinterUtil printerUtil = new PrinterUtil();

  public static void main(String[] args) {

    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(
            1,
            2,
            5,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            new CustomThreadFactory(),
            new CustomRejectedExecutionHandler());

    threadPoolExecutor.allowCoreThreadTimeOut(true);

    for (int taskNo = 1; taskNo <= 10; ++taskNo) {
      threadPoolExecutor.submit(new Task(taskNo));
    }

    threadPoolExecutor.shutdown();
  }

  static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      printerUtil.print("Rejected Task: {0}\n", r.toString());
    }
  }

  static class CustomThreadFactory implements ThreadFactory {

    int counter = 1;

    @Override
    public Thread newThread(Runnable r) {

      Thread thread = new Thread(r, "ThreadPool-" + counter++);
      thread.setPriority(Thread.NORM_PRIORITY);
      thread.setDaemon(false);
      return thread;
    }
  }

  static class Task implements Runnable {

    private final int taskNo;

    public Task(int taskNo) {
      this.taskNo = taskNo;
    }

    @Override
    public void run() {
      sleep(8);
      printerUtil.print("Task: - {0} is executed by: {1}\n", taskNo, threadName());
    }

    @Override
    public String toString() {
      return "Task-%s".formatted(this.taskNo);
    }
  }
}
