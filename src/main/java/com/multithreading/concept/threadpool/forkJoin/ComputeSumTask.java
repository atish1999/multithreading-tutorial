package com.multithreading.concept.threadpool.forkJoin;

import java.util.concurrent.RecursiveTask;

public class ComputeSumTask extends RecursiveTask<Integer> {

  private final int start;
  private final int end;

  public ComputeSumTask(int start, int end) {
    this.start = start;
    this.end = end;
  }


  @Override
  protected Integer compute() {

    if (end - start <= 4) {
      int sum = 0;
      for (int i = start; i <= end; ++i) {
        sum += i;
      }
      return sum;
    }

    int mid = (start + end) >>> 1;
    ComputeSumTask leftSubTask = new ComputeSumTask(start, mid);
    ComputeSumTask rightSubTask = new ComputeSumTask(mid + 1, end);

    leftSubTask.fork();
    rightSubTask.fork();


    int leftSum = leftSubTask.join();
    int rightSum = rightSubTask.join();

    return leftSum + rightSum;
  }
}
