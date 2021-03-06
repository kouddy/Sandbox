package algo.DP.doubleDimention;

public class MinNumCoins {
  public static int minNumCoins(int value, int[] coins) {
    int[] mins = new int[value + 1];
    for (int i = 0; i <= value; i++) {
      int min = Integer.MAX_VALUE;
      for (int j = 0; j < coins.length; j++) {
        if (i - coins[j] >= 0 && mins[i - coins[j]] < min) {
          min = mins[i - coins[j]];
        }
      }
      mins[i] = (min == Integer.MAX_VALUE) ? 0 : min + 1;
    }
    return mins[value];
  }

  public static void test() {
    System.out.println(minNumCoins(11, new int[] { 1, 3, 5 }));
  }
}
