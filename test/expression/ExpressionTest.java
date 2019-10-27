package expression;

public class ExpressionTest {
  public static void main(String[] args) {
    ExpressionTree et = new ExpressionTree("1.2");
    ExpressionTree et1 = new ExpressionTree("1.2 2 + ");
        //ExpressionTree et3 = new ExpressionTree("1.2 3 5 6 + /");
        //ExpressionTree et4 = new ExpressionTree("1.2 2");
    ExpressionTree et5 = new ExpressionTree("3 2 + 65.12 -");
    ExpressionTree et6 = new ExpressionTree("3 2 + 65.12  25 - *");

    System.out.println(et6.infix());
    System.out.println(et6.evaluate());
    System.out.println(et6.schemeExpression());
  }
}
