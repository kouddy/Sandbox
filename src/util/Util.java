package util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA. User: David Gao Date: 28/05/12 Time: 6:35 PM To
 * change this template use File | Settings | File Templates.
 */
public class Util {
  /**
   * @brief Given a integer, it will return hash of it.
   */
  public static BigInteger hash(BigInteger i) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      BigInteger ret_val = new BigInteger(md.digest(i.toByteArray()));
      return ret_val;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static long getDayInMillis(int day) {
    return getHoursInMillis(1) * 24;
  }

  public static long getHoursInMillis(int hour) {
    return getMinutesInMillis(1) * 60;
  }

  public static long getMinutesInMillis(int minute) {
    return getSecondsInMillis(1) * 60;
  }

  public static long getSecondsInMillis(int second) {
    return second * 1000;
  }

  public static long toSeconds(long time) {
    return time / 1000;
  }

  public static void printIntArray(int[] array) {
    int len = array.length;
    for (int i = 0; i < len; i++) {
      System.out.print(array[i] + ",");
    }
    System.out.print("\r\n");
  }
}
