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

  @Override
  protected Interval operation(Node t, Interval l, Interval r) {
    Interval newInterval = null;
    if (t.value.equals("U")) {
      newInterval = l.union(r);
    }
    if (t.value.equals("I")) {
      newInterval = l.intersect(r);
    }
    return newInterval;
  }

  @Override
  protected Interval buildEvaluateResult(Node node) {
    int start = 0;
    int end = 0;
    String[] intervalVal;
    intervalVal = node.value.split(",");
    start = Integer.parseInt(intervalVal[0]);
    end = Integer.parseInt(intervalVal[1]);
    return new Interval(start, end);
  }

  @Override
  protected Node createOperandNode(String input) {
    return new Node(input);
  }
}
