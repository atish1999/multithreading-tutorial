package com.multithreading.concept.threadpool.completableFuture;

import static com.multithreading.beans.ThreadPoolBean.getPoolExecutor;
import static com.multithreading.util.ColorUtil.colorize;
import static com.multithreading.util.ThreadUtil.sleep;
import static com.multithreading.util.ThreadUtil.threadName;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureExample {
  public static void main(String[] args) {

    completableFutureWithThenCombine();
  }

  private static void completableFutureWithThenCombine() {
    CompletableFuture<String> asyncTask1 =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("asyncTask1 executed by: " + threadName());
              sleep(5);
              return "Name: Atish";
            },
            getPoolExecutor());
    CompletableFuture<Integer> asyncTask2 =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("asyncTask2 executed by: " + threadName());
              sleep(5);
              return 123;
            },
            getPoolExecutor());

    asyncTask1
        .thenCombine(asyncTask2, (String val1, Integer val2) -> val1 + ", RollNo: " + 123)
        .thenAccept(
            ans -> {
              System.out.println(ans);
              getPoolExecutor().shutdown();
            });
  }

  private static void completableFutureWithThenAccept() {
    ThreadPoolExecutor poolExecutor = getPoolExecutor();
    CompletableFuture.supplyAsync(
            () -> {
              sleep(2);
              return 1;
            },
            poolExecutor)
        .thenAccept(
            x -> {
              System.out.println("final value = " + x);
              poolExecutor.shutdown();
            });
  }

  private static void completableFutureWithThenCompose() {
    CompletableFuture<String> asyncTask =
        CompletableFuture.supplyAsync(() -> "Hello")
            .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + "World"));

    print(asyncTask);
  }

  private static void testingCompletableFutureWithUnorderedTaskStartButChainedResultsPreserved() {
    List<Integer> turn = new ArrayList<>();
    for (int i = 1; i <= 100; ++i) {

      System.out.println(
          " Turn : " + i + " ------------------------------------------------------------ \n ");
      completableFutureWithUnorderedTaskStartButChainedResultsPreserved(turn, i);
      System.out.println(" ------------------------------------------------------------ \n ");
    }

    System.out.println("turns: " + turn);
  }

  public static void completableFutureWithUnorderedTaskStartButChainedResultsPreserved(
      List<Integer> turns, int i) {
    // Create a custom executor for running tasks asynchronously
    final String finalResult =
        "Step 1 -> Step 2 -> Step 3 -> Step 4 -> Step 5 -> Step 6 -> Step 7 -> Step 8";

    CompletableFuture.supplyAsync(
            () -> {
              System.out.println("Task 1 starts: " + threadName());
              System.out.println("Task 1 ends: " + threadName());
              return "Step 1";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 2 starts: " + threadName());
              System.out.println("Task 2 ends: " + threadName());
              return result + " -> Step 2";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 3 starts: " + threadName());
              System.out.println("Task 3 ends: " + threadName());
              return result + " -> Step 3";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 4 starts: " + threadName());
              System.out.println("Task 4 ends: " + threadName());
              return result + " -> Step 4";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 5 starts: " + threadName());
              System.out.println("Task 5 ends: " + threadName());
              return result + " -> Step 5";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 6 starts: " + threadName());
              System.out.println("Task 6 ends: " + threadName());
              return result + " -> Step 6";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 7 starts: " + threadName());
              System.out.println("Task 7 ends: " + threadName());
              return result + " -> Step 7";
            })
        .thenApplyAsync(
            result -> {
              System.out.println("Task 8 starts: " + threadName());
              System.out.println("Task 8 ends: " + threadName());
              return result + " -> Step 8";
            })
        .thenAccept(
            result -> {
              boolean equals = finalResult.equals(result);

              if (!equals) {
                turns.add(i);
              }

              System.out.println(
                  "Final result: "
                      + result
                      + " : -> "
                      + (equals ? colorize("YES", "\033[33m") : colorize("NO", "\033[33m")));
            })
        .join();

    // Wait for all tasks to complete
  }

  private static void completableFutureWithThenApplyAsyncWithACustomThreadPoolExecutor() {
    ThreadPoolExecutor poolExecutor = getPoolExecutor();
    CompletableFuture.supplyAsync(
            () -> {
              System.out.println("Thread executing supplyAsync = " + threadName());
              sleep(10);
              return "Hello From Supply Async";
            },
            poolExecutor)
        .thenApplyAsync(
            val -> {
              System.out.println("Thread executing thenApplyAsync = " + threadName());
              sleep(20);
              return val + " -> Hello From Then Apply Async";
            },
            poolExecutor);

    System.out.println("Thread name after CF: " + threadName());
    poolExecutor.shutdown();
  }

  private static void completableFutureWithThenApplyAsync() {
    Supplier<String> task1 =
        () -> {
          System.out.println("ThreadName of supplyAsync = " + threadName());
          sleep(8);
          return "Atish";
        };
    Function<String, String> task2 =
        val -> {
          System.out.println("ThreadName of thenApply = " + threadName());
          return val + " Naskar";
        };

    // both task1 and task2 will be executed by different thread as thenApplyAsync are getting used.

    CompletableFuture<String> asyncTask =
        CompletableFuture.supplyAsync(task1).thenApplyAsync(task2);
    print(asyncTask);
  }

  private static void completableFutureWithThenApply() {
    Supplier<String> task1 =
        () -> {
          System.out.println("ThreadName of supplyAsync = " + threadName());
          sleep(8);
          return "Atish";
        };
    Function<String, String> task2 =
        val -> {
          System.out.println("ThreadName of thenApply = " + threadName());
          return val + " Naskar";
        };

    // both task1 and task2 will be executed by same thread as thenApply is a synchronous operation

    CompletableFuture<String> asyncTask = CompletableFuture.supplyAsync(task1).thenApply(task2);
    print(asyncTask);
  }

  private static void createCompletableFutureWithCustomThreadPool() {
    ThreadPoolExecutor poolExecutor = getPoolExecutor();

    CompletableFuture<String> asyncTask1 =
        CompletableFuture.supplyAsync(
            () -> "Task is completed by: %s".formatted(threadName()), poolExecutor);

    print(asyncTask1);

    poolExecutor.shutdown();
  }

  static <T> void print(CompletableFuture<T> completableFuture) {
    try {
      T result = completableFuture.get();
      System.out.println("Output => " + result);
    } catch (Exception ignored) {

    }
  }
}
