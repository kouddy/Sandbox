package ece419;

import org.junit.Before;
import org.junit.Test;

public class TestCorrelationCalc {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() {
    CorrelationCalc.Calc("001", "0001");
  }
}
