package util;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

public class TestProfiler {

  Profiling profiler = new Profiling();

  @Test
  public void test_correctFlow1() throws Exception {
    long expectedTime = 4000;

    profiler.start();
    Thread.sleep(expectedTime);
    profiler.end();
    long actualTime = profiler.getDifference();
    profiler.print();

    Assert.assertTrue(assertPredicate(actualTime,
        i -> Math.abs(i.longValue() - expectedTime) / expectedTime * 100 < 2));
  }

  @Test
  public void test_correctFlow2() throws Exception {
    profiler.print();
  }

  @Test
  public void test_correctFlow3() throws Exception {
    long expectedTime = 4000;

    profiler.start();
    Thread.sleep(10);
    profiler.start();
    Thread.sleep(expectedTime);
    profiler.end();
    long actualTime = profiler.getDifference();
    profiler.print();

    Assert.assertTrue(assertPredicate(actualTime,
        i -> Math.abs(i.longValue() - expectedTime) / expectedTime * 100 < 2));
  }

  @Test(expected = Exception.class)
  public void test_wrongFlow1() throws Exception {
    profiler.start();
    profiler.print();
  }

  @Test(expected = Exception.class)
  public void test_wrongFlow2() throws Exception {
    profiler.end();
  }

  public <T> boolean assertPredicate(T actual, Predicate<? super T> predicate) {
    return predicate.test(actual);
  }
}
