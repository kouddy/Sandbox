package algo;

public class StringMatching {

  public static void lookAndSayTester() {
    lookAndSay("1", 1).equals("11");
    lookAndSay("1", 2).equals("21");
    lookAndSay("1", 3).equals("1211");
    lookAndSay("1", 4).equals("111221");
    lookAndSay("1", 5).equals("312211");
  }

  public static String lookAndSay(String seed, int n) {
    int numConsDigits = 0;
    char curDigit = '\0';
    String nextString = "";
    String curString = seed;

    for (int i = 0; i < n; i++) {
      numConsDigits = 1;
      curDigit = curString.charAt(0);

      for (int j = 0; j < curString.length() - 1; j++) {
        if (curString.charAt(j + 1) == curDigit) {
          numConsDigits++;
        } else {
          nextString = nextString + String.valueOf(numConsDigits) + curDigit;
          numConsDigits = 1;
          curDigit = curString.charAt(j + 1);
        }
      }
      nextString = nextString + String.valueOf(numConsDigits) + curDigit;

      curString = nextString;
      nextString = "";
    }
    return curString;
  }

  public static boolean match(String a, String b) {
    int lenA = a.length();
    int lenB = b.length();

    if (lenA < lenB) {
      return false;
    }

    for (int i = 0; i < (lenA - lenB + 1); i++) {
      int j = i;
      int k = 0;
      while (k < lenB) {
        if (b.charAt(k) == a.charAt(j)) {
          if (k == lenB - 1) {
            return true;
          }
          j++;
          k++;
        } else {
          break;
        }
      }
    }
    return false;
  }
}
