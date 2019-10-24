package expression;

import java.util.Stack;

/**
 * ExpressionTree that implements Expression interface to represents all the operations on an
 * expression tree, and evaluate it.
 */
public class ExpressionTree implements Expression {

  private String[] postfix;
  private Node root;

  /**
   * Construct an ExpresstionTree object given postfix string.
   *
   * @param postfix String of postfix
   */
  public ExpressionTree(String postfixS) {
    this.postfix = parsePostfixString(postfixS);
    root = createExpressionTree();
  }

  /**
   * Return a String array of postfix expression to create expression tree.
   *
   * @param postfix
   * @return String array of postfix
   */
  private String[] parsePostfixString(String postfixS) {
    postfixS = postfixS.replaceAll(" +", " ");
    String[] postfix = postfixS.split(" ");
    return postfix;
  }

  /** Node class for a binary tree. */
  class Node {
    String value;
    Node left;
    Node right;

    /**
     * Constructs a Node object given the value of the node.
     *
     * @param item the value of the node
     */
    public Node(String item) {
      value = item;
      left = right = null;
    }
  }

  /**
   * Check if the string is an operator or not.
   *
   * @param s String either an operand or an operator
   * @return true if the character is an operator false if not.
   */
  private boolean isOperator(String s) {
    if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
      return true;
    }
    return false;
  }

  /**
   * Create expression tree in the constructor.
   *
   * @return root
   */
  private Node createExpressionTree() {
    Node t;
    Node t1;
    Node t2;
    Stack<Node> nodeStack = new Stack<Node>();

    for (int i = 0; i < postfix.length; i++) {
      if (!isOperator(postfix[i])) {
        t = new Node(postfix[i]);
        nodeStack.push(t);
      } else {
        t = new Node(postfix[i]);
        t1 = nodeStack.pop();
        if (nodeStack.empty()) {
          throw new IllegalArgumentException("Postfix is incorrect!");
        }

        t2 = nodeStack.pop();
        t.left = t1;
        t.right = t2;
        nodeStack.push(t);
      }
    }

    t = nodeStack.pop();
    if (!nodeStack.empty()) {
      throw new IllegalAccessError("Postfix is incorrect!");
    }
    return t;
  }

  @Override
  public double evaluate() {
    Node t = root;
    return inOrderEvaluate(t);
  }

  /**
   * Inorder traverse to helper output infix expression.
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
   * Operate to get evaluated value of the given expression.
   *
   * @param t node
   * @param l value of left substree
   * @param r value of right subtree
   * @return the sum of left and right subtree value
   */
  private double operation(Node t, double l, double r) {
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

    return ans;
  }

  /**
   * Inorder traverse to calculate evaluated expression.
   *
   * @param t node
   * @return evaluated value
   */
  private Double inOrderEvaluate(Node t) {
    double leftV = 0;
    double rightV = 0;

    if (t == null) {
      return 0.0;
    }

    if (!isOperator(t.value)) {
      return (double) (Double.parseDouble(t.value));
    } else {
      leftV = inOrderEvaluate(t.left);
      rightV = inOrderEvaluate(t.right);

      return operation(t, leftV, rightV);
    }
  }

  /**
   * Preorder traverse to help output scheme expression.
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
}
