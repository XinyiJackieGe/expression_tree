package expression;

import common.AbstractTree;

/**
 * ExpressionTree that implements Expression interface to represents all the operations on an
 * expression tree, and evaluate it.
 */
public class ExpressionTree extends AbstractTree<Double> implements Expression {
  /**
   * Construct an expression tree.
   *
   * @param s input profix string
   */
  public ExpressionTree(String s) {
    super(s);
  }

  @Override
  public double evaluate() {
    Node t = root;
    Double result = inOrderEvaluate(t);
    return result.doubleValue();
  }

  @Override
  public String infix() {
    return inOrder(root);
  }

  @Override
  public String schemeExpression() {
    return preOrder(root);
  }

  @Override
  protected boolean isOperator(String s) {
    boolean res = s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");

    return res;
  }

  @Override
  protected Double buildEvaluateResult(Node node) {
    return Double.valueOf(node.value);
  }

  @Override
  protected Double operation(Node t, Double l, Double r) {
    double ans = 0;
    if (t.value.equals("+")) {
      ans = l + r;
    }
    if (t.value.equals("-")) {
      ans = l - r;
    }
    if (t.value.equals("*")) {
      ans = l * r;
    }
    if (t.value.equals("/")) {
      ans = l / r;
    }
    if (t.value.equals("^")) {
      ans = Math.pow(l, r);
    }
    return Double.valueOf(ans);
  }

  @Override
  protected Node createOperandNode(String input) {
    return new Node(Double.valueOf(input).toString(), true);
  }

  @Override
  protected Node createOperatorNode(String value, Node left, Node right) {
    if (!isOperator(value)) {
      throw new IllegalArgumentException("Invalid interval operator!");
    }

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
    if (node.value.equals("/")) {
      if (node.right.isOperand) {
        if (node.right.value.equals(String.valueOf(0))) {
          throw new IllegalArgumentException("Denominator cannot be zero!");
        }
      }
    }
  }

  /**
   * In-order traverse to helper output infix expression.
   *
   * @param t root node
   * @return infix String expression
   */
  private String inOrder(Node t) {
    String leftS = "";
    String rightS = "";

    if (t == null) {
      return "";
    }
    leftS = inOrder(t.left);
    rightS = inOrder(t.right);

    if (t.left == null && t.right == null) {
      return String.valueOf(t.value);
    }
    return "( " + leftS + " " + String.valueOf(t.value) + " " + rightS + " )";
  }

  /**
   * Pre-order traverse to help output scheme expression.
   *
   * @param t node
   * @return String of scheme expression
   */
  private String preOrder(Node t) {
    String leftS = "";
    String rightS = "";

    if (t == null) {
      return "";
    }

    leftS = preOrder(t.left);
    rightS = preOrder(t.right);

    if (t.left == null && t.right == null) {
      return String.valueOf(t.value);
    }
    return "(" + String.valueOf(t.value) + " " + leftS + " " + rightS + ")";
  }
}
