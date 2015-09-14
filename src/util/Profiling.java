package util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

/**
 * User: David Date: 01/08/12 Time: 10:28 AM To change this template use File |
 * Settings | File Templates.
 */
public class Profiling {
  // Stores function name and time in milli seconds.
  private HashMap<String, Long> profiles;
  // Used to know which methods are recorded in profiles.
  // The issue with this is that it can tell if a method had called Start() or
  // not,
  // but it can't tell if a method had called end() or not.
  // Also, sometimes due to throw of exception, End() might not have been
  // called.
  private HashMap<String, Boolean> storedMethods;

  private final String start = "Start: ";
  private final String end = "End: ";
  private final String appendix = "()";

  public Profiling() {
    profiles = new HashMap<String, Long>();
    storedMethods = new HashMap<String, Boolean>();
  }

  public void start() {
    final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    String methodName = ste[2].getMethodName();
    String className = ste[2].getClassName();
    String fullName = className + "." + methodName + appendix;

    String str = start + fullName;
    long time = Calendar.getInstance().getTimeInMillis();
    profiles.put(str, new Long(time));
    storedMethods.put(fullName, new Boolean(true));
  }

  public void end() throws Exception {
    final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    String methodName = ste[2].getMethodName();
    String className = ste[2].getClassName();
    String fullName = className + "." + methodName + appendix;

    // Check if called start or not;
    if (!storedMethods.get(fullName).booleanValue()) {
      throw new Exception("You forgot to call Start() in : " + fullName);
    }

    String str = end + fullName;
    long time = Calendar.getInstance().getTimeInMillis();

    profiles.put(str, new Long(time));
  }

  public void print() throws Exception {
    Set<String> methods = storedMethods.keySet();

    // System.out.println("Start printing profiling results.");
    for (String method : methods) {
      long diffInMillis = getDifference(method);
      long diffInSeconds = Util.toSeconds(diffInMillis);

      System.out.println(method + ":" + diffInMillis + "(ms), " + diffInSeconds
          + "(s)");
    }
    // System.out.println("End printing profiling results.");

    profiles.clear();
    storedMethods.clear();
  }

  public long getDifference() throws Exception {
    final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    String methodName = ste[2].getMethodName();
    String className = ste[2].getClassName();
    String method = className + "." + methodName + appendix;

    return getDifference(method);
  }

  private long getDifference(String method) throws Exception {
    String startStr = start + method;
    String endStr = end + method;

    Long startTime = profiles.get(startStr);
    Long endTime = profiles.get(endStr);

    if (null == startTime) {
      throw new Exception("You forgot to call Start() in : " + method);
    }

    if (null == endTime) {
      throw new Exception("You forgot to call End() in : " + method);
    }

    if (endTime.longValue() < startTime.longValue()) {
      throw new Exception("You called End() first then Start() in : " + method);
    }

    return endTime.longValue() - startTime.longValue();
  }
}
