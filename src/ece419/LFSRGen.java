package ece419;

import java.util.LinkedList;
import java.util.List;

public class LFSRGen {
  List<Boolean> q = new LinkedList<Boolean>();

  public LFSRGen(int num_bits) {
    initQ(num_bits);
  }

  public String GenSeq(int cycles) {
    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < cycles; i++) {
      Boolean x0 = q.get(0 + i);
      Boolean x1 = q.get(1 + i);
      Boolean x2 = q.get(2 + i);
      Boolean x3 = q.get(3 + i);
      Boolean newX4 = Boolean.valueOf(x0.booleanValue() ^ x3.booleanValue()); // x^5
                                                                              // +
                                                                              // x^3
                                                                              // +
                                                                              // 1
      newX4 = Boolean.valueOf(x0.booleanValue() ^ x1.booleanValue()
          ^ x2.booleanValue() ^ x3.booleanValue());

      if (true == x0.booleanValue()) {
        sb.append(1);
      } else {
        sb.append(0);
      }

      q.add(newX4);
    }
    clearQ();
    return sb.toString();
  }

  private void initQ(int num_bits) {
    for (int i = 0; i < num_bits; i++) {
      // // x^5 + x^3 + 1
      // if( 0 == i) {
      // q.add( Boolean.valueOf(true) );
      // }
      // else {
      // q.add( Boolean.valueOf(false) );
      // }
      if ((num_bits - 1) == i) {
        q.add(Boolean.valueOf(true));
      } else {
        q.add(Boolean.valueOf(false));
      }

    }
  }

  private void clearQ() {
    q = new LinkedList<Boolean>();
    initQ(5);
  }
}
