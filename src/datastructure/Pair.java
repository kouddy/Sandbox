package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Pair {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] firstLine = br.readLine().split(" ");
    int n = Integer.parseInt(firstLine[0]);
    int k = Integer.parseInt(firstLine[1]);

    String[] secondLine = br.readLine().split(" ");
    int[] vals = new int[n];
    Set<Integer> numbers = new HashSet<Integer>();
    for (int i = 0; i < n; i++) {
      vals[i] = Integer.parseInt(secondLine[i]);
      numbers.add(Integer.valueOf(vals[i]));
    }

    int count = 0;
    for (int i = 0; i < n; i++) {
      if (numbers.contains(Integer.valueOf(vals[i] + k))) {
        count++;
      }
    }
    // for( int i = 0; i < n-1; i++ ) {
    // for( int j = i+1; j < n; j++ ) {
    // int diff = vals[i] - vals[j];
    // if( diff == k || diff == -1 * k ) {
    // count++;
    // }
    // }
    // }
    System.out.println(count);

    br.close();
    br = null;
    vals = null;
  }
}