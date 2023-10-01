// Exercise29_4.java: 1000 threads, each adds 1 to a variable sum
import java.util.concurrent.*;

public class Exercise29_4 {
  private Integer sum = new Integer(0);

  public static void main(String[] args) {
    Exercise29_4 test = new Exercise29_4();
    System.out.println("What is sum ? " + test.sum);
  }

  public Exercise29_4() {
    ExecutorService executor = Executors.newFixedThreadPool(1000);

    for (int i = 0; i < 1000; i++) {
      executor.execute(new SumTask());
    }

    executor.shutdown();

    while(!executor.isTerminated()) {
    }
  }

  class SumTask implements Runnable {
    public void run() {
      int value = sum.intValue() + 1;
      sum = new Integer(value);
    }
  }
}
