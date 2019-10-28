import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import expression.Expression;
import expression.ExpressionTree;

/** JUnit tests for ExpressionTree. */
public class ExpressionTest {
  public Expression et1;
  public Expression et3;
  public Expression et4;
  public Expression et5;
  public Expression et6;
  public Expression et7;

  @Before
  public void setup() {
    et1 = new ExpressionTree("1 5 *   -4 + ");
    et3 = new ExpressionTree("1 2 + 5 3 - *");
    et4 = new ExpressionTree(" 1 2 + 5 3 - * 2 /");
    et5 = new ExpressionTree("2");
    et6 = new ExpressionTree("1 0 ^");
    et7 = new ExpressionTree("1 4 6 - 5 + /");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExcetption_1() {
    new ExpressionTree("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExcetption_2() {
    new ExpressionTree("1 5");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExcetption_3() {
    new ExpressionTree("1.5 3.2 5.3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExcetption_4() {
    new ExpressionTree(" 1.0 0.0 / "); // Denominator cannot be zero.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExcetption_5() {
    new ExpressionTree(" a 5 +"); // Invalid operand.
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExcetption_6() {
    new ExpressionTree(" 1 5 &"); // Invalid operator.
  }

  @Test
  public void testInfix() {
    String str1 = "( ( 1.0 * 5.0 ) + -4.0 )";
    assertEquals(str1, et1.infix());

    String str3 = "( ( 1.0 + 2.0 ) * ( 5.0 - 3.0 ) )";
    assertEquals(str3, et3.infix());

    String str4 = "( ( ( 1.0 + 2.0 ) * ( 5.0 - 3.0 ) ) / 2.0 )";
    assertEquals(str4, et4.infix());

    String str5 = "2.0";
    assertEquals(str5, et5.infix());

    String str6 = "( 1.0 ^ 0.0 )";
    assertEquals(str6, et6.infix());
  }

  @Test
  public void testSchemeExpression() {
    String str1 = "(+ (* 1.0 5.0) -4.0)";
    assertEquals(str1, et1.schemeExpression());

    String str3 = "(* (+ 1.0 2.0) (- 5.0 3.0))";
    assertEquals(str3, et3.schemeExpression());

    String str4 = "(/ (* (+ 1.0 2.0) (- 5.0 3.0)) 2.0)";
    assertEquals(str4, et4.schemeExpression());

    String str5 = "2.0";
    assertEquals(str5, et5.schemeExpression());

    String str6 = "(^ 1.0 0.0)";
    assertEquals(str6, et6.schemeExpression());
  }

  @Test
  public void testEvaluate() {
    assertEquals(1.0, et1.evaluate(), 0.00001);
    // assertEquals(Double.MAX_VALUE,expCase2.evaluate(),0.00001);
    assertEquals(6.0, et3.evaluate(), 0.00001);
    assertEquals(3.0, et4.evaluate(), 0.00001);
    assertEquals(1.0, et6.evaluate(), 0.00001);
  }

  @Test
  public void testTextTree() {
    StringBuilder sb = new StringBuilder();
    sb.append("/\n");
    sb.append("|\n");
    sb.append("|\n");
    sb.append("|___1.0\n");
    sb.append("|\n");
    sb.append("|___+\n");
    sb.append("    |\n");
    sb.append("    |\n");
    sb.append("    |___-\n");
    sb.append("    |   |\n");
    sb.append("    |   |\n");
    sb.append("    |   |___4.0\n");
    sb.append("    |   |\n");
    sb.append("    |   |___6.0\n");
    sb.append("    |\n");
    sb.append("    |___5.0");
    assertEquals(sb.toString(), et7.textTree());
  }
}
