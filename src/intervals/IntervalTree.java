package intervals;

import common.AbstractTree;

/**
 * IntervalTree that implements Intervals interface to represents an interval tree, and evaluate it.
 */
public class IntervalTree extends AbstractTree<Interval> implements Intervals {
  /**
   * Construct an interval tree.
   *
   * @param s postfix string input
   */
  public IntervalTree(String s) {
    super(s);
  }

  @Override
  protected boolean isOperator(String s) {
    boolean res = s.equalsIgnoreCase("U") || s.equalsIgnoreCase("I");
    return res;
  }

  @Override
  public Interval evaluate() {
    return inOrderEvaluate(root);
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
    String[] intervalVal = node.value.split(",");
    int start = Integer.parseInt(intervalVal[0]);
    int end = Integer.parseInt(intervalVal[1]);
    return new Interval(start, end);
  }

  @Override
  protected Node createOperandNode(String input) {
    Node operand = new Node(input, true);
    String[] intervalVal = input.split(",");
    int start = Integer.parseInt(intervalVal[0]);
    int end = Integer.parseInt(intervalVal[1]);
    if (start > end) {
      throw new IllegalArgumentException("Lower bound cannot larger than upper bound!");
    }
    return operand;
  }

  @Override
  protected Node createOperatorNode(String value, Node left, Node right) {
    Node operator = new Node(value, false);
    operator.left = left;
    operator.right = right;
    checkOperatorNode(operator);
    return operator;
  }

  /**
   * Check if operator and operands are valid.
   *
   * @param node operator node
   */
  private void checkOperatorNode(Node node) {
    if (!isOperator(node.value)) {
      throw new IllegalArgumentException("Invalid interval operator!");
    }
  }
}
