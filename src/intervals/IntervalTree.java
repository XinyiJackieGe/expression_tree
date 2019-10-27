package intervals;

import common.AbstractTree;

/**
 * IntervalTree that implements Intervals interface to represents an interval tree, and evaluate it.
 */
public class IntervalTree extends AbstractTree<Interval> implements Intervals {

  @Override
  protected boolean isOperator(String s) {
    if (s.equalsIgnoreCase("U") || s.equalsIgnoreCase("I")) {
      return true;
    }
    return false;
  }

  public IntervalTree(String postfixS) {
    super(postfixS);
  }

  @Override
  public Interval evaluate() { // type different, others are the same.
    Node t = root;
    return inOrderEvaluate(t);
  }

  //  /**
  //   * FIXME
  //   * @param t
  //   * @return
  //   */
  //  private String preOrder(Node t) {
  //    String leftS = "";
  //    String rightS = "";
  //
  //    if (t == null) {
  //      return "";
  //    }
  //
  //    leftS = preOrder(t.left);
  //    rightS = preOrder(t.right);
  //
  //    if (t.left == null && t.right == null) {
  //      return String.valueOf(t.value);
  //    }
  //    return "(" + String.valueOf(t.value) + " " + leftS + " " + rightS + ")";
  //  }

  //  /**
  //   * FIXME
  //   * @return
  //   */
  //  public String schemeExpression() {
  //    Node t = root;
  //    String schemeExpressionS = preOrder(t);
  //
  //    return schemeExpressionS;
  //  }

  @Override
  protected Interval operation(AbstractTree<Interval>.Node t, Interval l, Interval r) {
    Interval newInterval = null;
    ;
    if (t.value.equals("U")) {
      newInterval = l.union(r);
    }
    if (t.value.equals("I")) {
      newInterval = l.intersect(r);
    }

    return newInterval;
  }

  @Override
  protected Interval buildEvaluateResult(AbstractTree<Interval>.Node node) {
    int start = 0;
    int end = 0;
    String[] intervalVal;
    intervalVal = node.value.split(",");
    start = Integer.parseInt(intervalVal[0]);
    end = Integer.parseInt(intervalVal[1]);
    Interval evaluatedInterval = new Interval(start, end);

    return evaluatedInterval;
  }
}
