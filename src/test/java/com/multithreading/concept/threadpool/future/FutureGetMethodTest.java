package com.multithreading.concept.threadpool.future;

import static com.multithreading.util.ThreadUtil.sleep;
import static com.multithreading.util.ThreadUtil.threadName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FutureGetMethodTest {

  private static FutureGetMethod futureGetMethod;

  @BeforeAll
  static void setUp() {
    futureGetMethod = new FutureGetMethod();
  }

  @Test
  void
      givenTaskTaking4Seconds_whenFutureGetCalledWith2SecondTimeout_thenTimeoutExceptionIsThrown() {

    ThreadPoolExecutor poolExecutor = futureGetMethod.getExecutor();

    Future<?> future =
        poolExecutor.submit(
            () -> {
              sleep(4);
              System.out.println("task is executed by = " + threadName());
            });

    System.out.println("TASK-STATUS: " + future.isDone());
    assertFalse(future.isDone());
    // waiting for 2 seconds if time is crossing more than that then TimeOutException will occurr
    assertThrows(TimeoutException.class, () -> future.get(2, TimeUnit.SECONDS));
    assertFalse(future.isCancelled());
  }

  @Test
  void
      givenTaskTaking2Seconds_whenFutureGetCalledWith3SecondTimeout_thenNoTimeOutExceptionWillOccur() {

    ThreadPoolExecutor poolExecutor = futureGetMethod.getExecutor();

    Future<?> future =
        poolExecutor.submit(
            () -> {
              sleep(2);
              System.out.println("task is executed by = " + threadName());
            });

    System.out.println("TASK-STATUS: " + future.isDone());
    assertDoesNotThrow(() -> future.get(3, TimeUnit.SECONDS));
    assertTrue(future.isDone());
    assertFalse(future.isCancelled());
  }

  @Test
  void
      givenTaskTaking4Seconds_whenGetWith3SecondTimeout_thenThrowsTimeoutException_and_whenGetWithoutTimeout_thenCompletesSuccessfully() {

    ThreadPoolExecutor poolExecutor = futureGetMethod.getExecutor();

    Future<?> future =
        poolExecutor.submit(
            () -> {
              sleep(4);
              System.out.println("task is executed by = " + threadName());
            });

    System.out.println("TASK-STATUS: " + future.isDone());
    assertFalse(future.isDone());
    assertThrows(TimeoutException.class, () -> future.get(3, TimeUnit.SECONDS));

    assertDoesNotThrow(() -> future.get());
    assertTrue(future.isDone());
    assertFalse(future.isCancelled());
  }
}
