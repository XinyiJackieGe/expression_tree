import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import intervals.Intervals;
import intervals.IntervalTree;

/** JUnit tests for IntervalTree. */
public class IntervalTreeTest {
  public Intervals it1;
  public Intervals it2;
  public Intervals it3;
  public Intervals it4;
  public Intervals it5;

  @Before
  public void setup() {
    it1 = new IntervalTree("1,5");
    it2 = new IntervalTree("1,5 2,8 I 4,7 U");
    it3 = new IntervalTree("1,4 5,8 I");
    it5 = new IntervalTree("1,2 3,4 4,6 U 3,5 I U");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException_1() {
    new IntervalTree("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException_2() {
    new IntervalTree("3,-4"); // Lower bound greater than upper bound.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException_3() {
    new IntervalTree("3,-4 2,5 I");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException_4() {
    new IntervalTree("3,-4 2,5 A"); // Invalid operator.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException_5() {
    new IntervalTree("3,a 2,5 A"); // Invalid operator.
  }

  @Test
  public void testEvaluate() {
    String str1 = "1,5";
    assertEquals(str1, it1.evaluate().toString());

    String str2 = "2,7";
    assertEquals(str2, it2.evaluate().toString());
  }

  @Test
  public void testTextTree() {
    StringBuilder sb = new StringBuilder();
    sb.append("U\n");
    sb.append("|\n");
    sb.append("|\n");
    sb.append("|___1,2\n");
    sb.append("|\n");
    sb.append("|___I\n");
    sb.append("    |\n");
    sb.append("    |\n");
    sb.append("    |___U\n");
    sb.append("    |   |\n");
    sb.append("    |   |\n");
    sb.append("    |   |___3,4\n");
    sb.append("    |   |\n");
    sb.append("    |   |___4,6\n");
    sb.append("    |\n");
    sb.append("    |___3,5");
    assertEquals(sb.toString(), it5.textTree());
  }
}
