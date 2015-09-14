package ece419;

public class CorrelationCalc {
  public static void Calc(String seqA, String seqB) {
    int len = Math.max(seqA.length(), seqB.length());
    for (int i = 0; i < len * 2; i++) {
      System.out.print(calcSingle(seqA, shift(seqB, i)) + " ");
    }
    System.out.print("\r\n");
  }

  private static int calcSingle(String seqA, String seqB) {
    int sum = 0;
    int len = Math.max(seqA.length(), seqB.length());
    try {
      for (int i = 0; i < len; i++) {
        int seqAIndex = i % seqA.length();
        int seqBIndex = i % seqB.length();
        int a = Integer.parseInt(String.valueOf(seqA.charAt(seqAIndex)));
        int b = Integer.parseInt(String.valueOf(seqB.charAt(seqBIndex)));
        int result = a ^ b;
        if (0 == result) {
          sum = sum + 1;
        } else {
          sum = sum - 1;
        }
      }
      return sum;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sum;
  }

  private static String shift(String seq, int shiftBit) {
    int len = seq.length();
    String appendix = "";
    String prefix = "";
    int actualShiftBit = shiftBit % len;

    if (0 != actualShiftBit) {
      prefix = seq.substring(len - actualShiftBit);
      appendix = seq.substring(0, (len - actualShiftBit));
    } else {
      prefix = seq;
    }
    return prefix + appendix;
  }
}
