package expression;

import common.AbstractTree;

/**
 * ExpressionTree that implements Expression interface to represents all the operations on an
 * expression tree, and evaluate it.
 */
public class ExpressionTree extends AbstractTree<Double> implements Expression {

  @Override
  protected boolean isOperator(String s) {
    if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
      return true;
    }
    return false;
  }

  public ExpressionTree(String postfixS) {
    super(postfixS);
  }

  @Override
  public double evaluate() {
    Node t = root;
    Double result = inOrderEvaluate(t);
    return result.doubleValue();
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

  @Override
  public String infix() {
    Node t = root;
    String infixS = inOrder(t);

    return infixS;
  }

  @Override
  public String schemeExpression() {
    Node t = root;
    String schemeExpressionS = preOrder(t);

    return schemeExpressionS;
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
    return new Node(Double.valueOf(input).toString());
  }
}
