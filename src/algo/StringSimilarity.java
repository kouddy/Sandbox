package algo;

public class StringSimilarity {
  public static void compare(String str) {
    int count = 0;
    int len = str.length();
    for (int i = 0; i < len; i++) {
      String suffix = str.substring(i);
      int suffixLen = suffix.length();
      for (int j = 0; j < suffixLen; j++) {
        if (suffix.charAt(j) != str.charAt(j)) {
          break;
        }
        count++;
      }
    }
    System.out.println(count);
  }
}
