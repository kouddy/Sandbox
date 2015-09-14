package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class CourseScheduler {
  public String[] scheduleCourses(String[] allCourses) {
    // Graph of <prerequisite course name, list of courses which depends on the
    // prerequisite course>.
    // Storing this way to avoid bottleneck of reversing direction of edges when
    // doing topological sort.
    HashMap<String, ArrayList<String>> coursesGraph = new HashMap<String, ArrayList<String>>();

    // Table that stores number of prerequisites for each course.
    // This table also contains all of the courses which appears on left of ":"
    // as keys.
    HashMap<String, Integer> numPrerequisites = new HashMap<String, Integer>();

    String[] retArr = new String[0];

    for (String course : allCourses) {
      // Check format using regular expression.
      if (!course.matches("[A-Z]{3,4}[1-9]\\d\\d:( [A-Z]{3,4}[1-9]\\d\\d)*")) {
        return retArr;
      }
      buildGraph(coursesGraph, numPrerequisites, course);
    }
    // Check if courses.keySet() <=> courses on left of ":" (which is
    // numPrerequisites.keySet()).
    if (!checkEquivalency(coursesGraph, numPrerequisites)) {
      return retArr;
    }

    // Return order to take the courses.
    retArr = topologicalSort(coursesGraph, numPrerequisites);

    coursesGraph = null;
    numPrerequisites = null;
    return retArr;
  }

  /**
   * @description Build graph for given string of "course:its prerequisites".
   * @param graph
   *          - Graph to build.
   * @param numPrerequisites
   *          - Table that stores number of prerequisites for each course.
   * @param courseWithPrerequisites
   *          - String of "course:its prerequisites".
   */
  private void buildGraph(HashMap<String, ArrayList<String>> graph,
      HashMap<String, Integer> numPrerequisites, String courseWithPrerequisites) {
    String[] splitted = courseWithPrerequisites.split(":");
    String course = splitted[0];
    String[] prerequisite = null;

    // Put course in table
    if (!graph.containsKey(course)) {
      graph.put(course, new ArrayList<String>());
    }

    // Have prerequisite, add edges (prereq course -> course).
    if (2 == splitted.length) {
      // We don't need first char since it's a space.
      splitted[1] = splitted[1].substring(1);
      prerequisite = splitted[1].split(" ");

      for (String p : prerequisite) {
        ArrayList<String> dependedBy = !graph.containsKey(p) ? new ArrayList<String>()
            : graph.get(p);
        dependedBy.add(course);
        graph.put(p, dependedBy);
      }
      numPrerequisites.put(course, Integer.valueOf(prerequisite.length));
    } else {
      numPrerequisites.put(course, Integer.valueOf(0));
    }
  }

  /**
   * @description - Check if courses.keySet() <=> numPrerequisites.keySet().
   * @return - True if they are equivalent ( By means of <=> ).
   */
  private boolean checkEquivalency(
      HashMap<String, ArrayList<String>> coursesGraph,
      HashMap<String, Integer> numPrerequisites) {
    String[] a = coursesGraph.keySet().toArray(new String[0]);
    String[] b = numPrerequisites.keySet().toArray(new String[0]);
    if (a.length != b.length) {
      return false;
    }
    Arrays.sort(a);
    Arrays.sort(b);

    for (int i = 0; i < a.length; i++) {
      if (!a[i].equals(b[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * @description - sort graph and return order to take the courses.
   * @param graph
   *          - Graph to sort.
   * @param numPrerequisites
   *          - Table that stores number of prerequisites for each course.
   * @return - Order to take the courses.
   */
  private String[] topologicalSort(HashMap<String, ArrayList<String>> graph,
      HashMap<String, Integer> numPrerequisites) {
    String[] sortedOrder = new String[graph.size()];
    int sortedOrderTail = 0;

    while (!graph.isEmpty()) {
      // "ZZZZ" is smaller than "ZZZZZ".
      String min = "ZZZZZ999";
      Set<String> courses = numPrerequisites.keySet();
      // Find which course to take next.
      for (String course : courses) {
        if ((numPrerequisites.get(course).intValue() == 0)
            && 0 < compare(min, course)) {
          min = course;
        }
      }
      // if no cycle then remove from graph and numPrerequisites
      if (!min.equals("ZZZZZ999")) {
        sortedOrder[sortedOrderTail++] = min;
        numPrerequisites.remove(min);

        ArrayList<String> coursesToDecrement = graph.remove(min);
        for (String c : coursesToDecrement) {
          int val = numPrerequisites.get(c).intValue();
          val--;
          numPrerequisites.put(c, Integer.valueOf(val));
        }
      } else {
        return new String[0];
      }
    }
    return sortedOrder;
  }

  /**
   * @description - Custom String comparison method for the exercise.
   * @param a
   *          - First String.
   * @param b
   *          - Second String.
   * @return Positive if a > b, equal if a == b, negative if a < b.
   */
  private int compare(String a, String b) {
    // Compare course numbers.
    int val = a.substring(a.length() - 1 - 3).compareTo(
        b.substring(b.length() - 1 - 3));
    if (val != 0) {
      return val;
    }
    // Compare course department.
    return a.substring(0, a.length() - 1 - 3).compareTo(
        b.substring(0, b.length() - 1 - 3));
  }
}
