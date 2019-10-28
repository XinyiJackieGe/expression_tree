package common;

import java.util.Stack;

/**
 * Abstract class to extract common fields and methods for both ExpressionTree and IntervalTree.
 *
 * @param <T> A generic type of nodes for the tree
 */
public abstract class AbstractTree<T> {
  protected String[] postfix;
  protected Node root;

  /** Node class for a binary tree. */
  protected static class Node {
    public String value;
    public Node left;
    public Node right;

    /**
     * Constructs a Node object given the value of the node.
     *
     * @param item the value of the node
     */
    public Node(String item) {
      value = item;
      left = right = null;
    }

    @Override
    public String toString() {
      if (left == null || right == null) {
        return value;
      }
      StringBuilder sb = new StringBuilder();
      sb.append("(V:")
          .append(value)
          .append(", L:")
          .append(left)
          .append(", R:")
          .append(right)
          .append(")");
      return sb.toString();
    }
  }

  /**
   * Conduct operation on children.
   *
   * @param t node t
   * @param l t left value
   * @param r r right value
   * @return T value
   */
  protected abstract T operation(Node t, T l, T r);

  /**
   * Build leaf value.
   *
   * @param node either a Double or an Interval
   * @return T value
   */
  protected abstract T buildEvaluateResult(Node node);

  /**
   * Check if the string is an operator or not.
   *
   * @param s String either an operand or an operator
   * @return true if the character is an operator false if not.
   */
  protected abstract boolean isOperator(String s);

  /**
   * Construct an ExpresstionTree object given postfix string.
   *
   * @param postfix String of postfix
   */
  protected AbstractTree(String postfix) {
    this.postfix = parsePostfixString(postfix);
    root = constructTree();
  }

  /**
   * Return a String array of postfix expression to create expression tree.
   *
   * @param postfix
   * @return String array of postfix
   */
  protected String[] parsePostfixString(String postfix) {
    postfix = postfix.replaceAll(" +", " ");
    return postfix.split(" ");
  }

  private Node createOperatorNode(String input) {
    return new Node(input);
  }

  protected abstract Node createOperandNode(String input);

  /**
   * Create expression tree in the constructor.
   *
   * @return root
   */
  protected Node constructTree() { // same
    Node t;
    Node t1;
    Node t2;
    Stack<Node> nodeStack = new Stack<Node>();

    for (int i = 0; i < postfix.length; i++) {
      if (!isOperator(postfix[i])) {
        t = createOperandNode(postfix[i]);
        nodeStack.push(t);
      } else {
        t = createOperatorNode(postfix[i]);
        t1 = nodeStack.pop();
        if (nodeStack.empty()) {
          throw new IllegalArgumentException("Postfix is incorrect!");
        }
        t2 = nodeStack.pop();
        t.left = t2;
        t.right = t1;
        nodeStack.push(t);
      }
    }

    t = nodeStack.pop();
    if (!nodeStack.empty()) {
      throw new IllegalAccessError("Postfix is incorrect!");
    }
    return t;
  }

  protected T inOrderEvaluate(Node t) {
    if (t != null) {
      if (!isOperator(t.value)) {
        return buildEvaluateResult(t);
      } else {
        T leftI = inOrderEvaluate(t.left);
        T rightI = inOrderEvaluate(t.right);
        return operation(t, leftI, rightI);
      }
    }
    return null;
  }

  /**
   * Help return text tree for printTextTree.
   *
   * @param t node other than root
   * @param indent string indent
   * @param last boolean true if it is right node false left node
   */
  protected void printTextTreeHelper(Node t, String indent, boolean last, StringBuilder sb) {
    if (t == null) {
      return;
    }

    sb.append(indent + "|\n");
    if (!last) {
      sb.append(indent + "|\n");
    }
    sb.append(indent + "|___" + t.value + "\n");
    indent += last ? "    " : "|   ";

    printTextTreeHelper(t.left, indent, false, sb);
    printTextTreeHelper(t.right, indent, true, sb);
  }

  public String textTree() {
    StringBuilder sb = new StringBuilder();
    sb.append(root.value + "\n");
    printTextTreeHelper(root.left, "", false, sb);
    printTextTreeHelper(root.right, "", true, sb);
    return sb.toString();
  }
}
