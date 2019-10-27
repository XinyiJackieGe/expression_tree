import intervals.IntervalTree;

public class IntervalTreeTest {
  public static void main(String[] args) {
    IntervalTree it = new IntervalTree("1,4 2,5 U");

    System.out.println(it.evaluate());

    System.out.println(it.textTree());
  }
}
