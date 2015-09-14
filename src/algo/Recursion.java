package algo;

public class Recursion {

  public static int FibonacciRec(int n) {
    if ((n == 0) || (n == 1)) {
      return 1;
    } else {
      return FibonacciRec(n - 1) + FibonacciRec(n - 2);
    }
  }

  public static int Fibonacci(int n) {
    if ((n == 0) || (n == 1)) {
      return 1;
    } else {
      int base0 = 1;
      int base1 = 1;
      for (int i = 2; i <= n; i++) {
        if ((i % 2) == 0) {
          base1 = base0 + base1;
        } else {
          base0 = base0 + base1;
        }
      }
      if ((n % 2) == 0) {
        return base1;
      } else {
        return base0;
      }
    }
  }

  public static int FibonacciDyn(int n) {
    int[] array = new int[n + 1];
    array[0] = 1;
    array[1] = 1;
    for (int i = 2; i < n; i++) {
      array[i] = 0;
    }
    return _Fib(n, array);
  }

  private static int _Fib(int n, int[] array) {
    if (array[n] == 0) {
      array[n] = _Fib(n - 1, array) + _Fib(n - 2, array);
    }
    return array[n];
  }
}
