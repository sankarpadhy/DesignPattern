package behavioral.interpreter;

public class InterpreterDemo {
    public static void main(String[] args) {
        // Interpret (5 + 10) - 3
        Expression five = new NumberExpression(5);
        Expression ten = new NumberExpression(10);
        Expression three = new NumberExpression(3);
        
        Expression add = new AddExpression(five, ten);
        Expression subtract = new SubtractExpression(add, three);
        
        System.out.println("(5 + 10) - 3 = " + subtract.interpret());
        
        // Interpret 20 - (4 + 6)
        Expression twenty = new NumberExpression(20);
        Expression four = new NumberExpression(4);
        Expression six = new NumberExpression(6);
        
        Expression add2 = new AddExpression(four, six);
        Expression subtract2 = new SubtractExpression(twenty, add2);
        
        System.out.println("20 - (4 + 6) = " + subtract2.interpret());
    }
}
