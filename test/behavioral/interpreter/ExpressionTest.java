package behavioral.interpreter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {
    
    @Test
    void testNumberExpression() {
        Expression number = new NumberExpression(5);
        assertEquals(5, number.interpret());
    }
    
    @Test
    void testAddExpression() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(3);
        Expression add = new AddExpression(left, right);
        
        assertEquals(8, add.interpret());
    }
    
    @Test
    void testSubtractExpression() {
        Expression left = new NumberExpression(10);
        Expression right = new NumberExpression(4);
        Expression subtract = new SubtractExpression(left, right);
        
        assertEquals(6, subtract.interpret());
    }
    
    @Test
    void testComplexExpression() {
        // Testing (15 + 5) - (10 + 2)
        Expression fifteen = new NumberExpression(15);
        Expression five = new NumberExpression(5);
        Expression ten = new NumberExpression(10);
        Expression two = new NumberExpression(2);
        
        Expression add1 = new AddExpression(fifteen, five);
        Expression add2 = new AddExpression(ten, two);
        Expression subtract = new SubtractExpression(add1, add2);
        
        assertEquals(8, subtract.interpret());
    }
    
    @Test
    void testNegativeResult() {
        Expression left = new NumberExpression(5);
        Expression right = new NumberExpression(10);
        Expression subtract = new SubtractExpression(left, right);
        
        assertEquals(-5, subtract.interpret());
    }
    
    @Test
    void testZeroResult() {
        Expression number = new NumberExpression(5);
        Expression subtract = new SubtractExpression(number, number);
        
        assertEquals(0, subtract.interpret());
    }
}
