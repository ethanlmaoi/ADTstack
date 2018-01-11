/**
 * Name: Liao, Ethan
 * Assignment: Project #3
 * Due: 5/25/2017
 * Course: cs-240-02
 * Description: This project requires me to implement two class methods that can
 * convert an infix expression to its equivalent postfix and evaluate the postfix
 * expression.
 */

/**
 * This main class will thoroughly test the following two class methods in the Expression class:
 * 1) convertToPostfix
 * 2) evaluatePostfix
 * Will put these methods through several test cases.
 * 
 * @author Ethan Liao
 */
public class ExpressionTest 
{
    public static void main(String args[])
    {
        System.out.println("INFIX CONVERSION: Test #1:");
        System.out.print("INFIX: ");
        String[] infixExpressionA = {"a","+","b","+","c"}; // infix expression a+b+c
        printExpression(infixExpressionA);       
        System.out.print("POSTFIX: ");
        printExpression(Expression.convertToPostfix(infixExpressionA));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Test #2 (with numbers):");
        System.out.print("INFIX: ");
        String[] infixExpressionB = {"5","+","3","*","2"}; // infix expression a+b*c
        printExpression(infixExpressionB);       
        System.out.print("POSTFIX: ");
        printExpression(Expression.convertToPostfix(infixExpressionB));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Test #3:");
        System.out.print("INFIX: ");
        String[] infixExpressionC = {"(","a","/","b",")","*","(","c","-","d",")"}; // infix expression (a/b)*(c-d)
        printExpression(infixExpressionC);       
        System.out.print("POSTFIX: ");
        printExpression(Expression.convertToPostfix(infixExpressionC));
        System.out.println();

        System.out.println("INFIX CONVERSION: Test #4:");
        System.out.print("INFIX: ");
        String[] infixExpressionJ = {"a","*","(","b","*","(","c","+","d",")",")"}; // infix expression (a/b)*(c-d)
        printExpression(infixExpressionJ);       
        System.out.print("POSTFIX: ");
        printExpression(Expression.convertToPostfix(infixExpressionJ));
        System.out.println();
                
        System.out.println("INFIX CONVERSION: Special Test Case #1");
        System.out.println("Checks when there are less than 3 variables and operators combined.");
        System.out.print("INFIX: ");
        String[] infixExpressionD = {"a","+"}; // infix expression a+
        printExpression(infixExpressionD);
        printExpression(Expression.convertToPostfix(infixExpressionD));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Special Test Case #2 (with numbers)");
        System.out.println("Checks when two operators are next to each other.");
        System.out.print("INFIX: ");
        String[] infixExpressionE = {"5","+","3","+","-","2"}; // infix expression a+b+-c
        printExpression(infixExpressionE);
        printExpression(Expression.convertToPostfix(infixExpressionE));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Special Test Case #3 (with numbers)");
        System.out.println("Checks when two variables are next to each other.");
        System.out.print("INFIX: ");
        String[] infixExpressionF = {"7","8","+","4"}; // infix expression ab+c
        printExpression(infixExpressionF);
        printExpression(Expression.convertToPostfix(infixExpressionF));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Special Test Case #4");
        System.out.println("Checks when there are an unbalanced number of right and left parentheses.");
        System.out.print("INFIX: ");
        String[] infixExpressionG = {"(","(","+","b",")","*","(","c","-","d",")"}; // infix expression ((+b)*(c-d)
        printExpression(infixExpressionG);
        printExpression(Expression.convertToPostfix(infixExpressionG));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Special Test Case #5");
        System.out.println("Checks when there are no variables present.");
        System.out.print("INFIX: ");
        String[] infixExpressionH = {"(","+",")"}; // infix expression (+)
        printExpression(infixExpressionH);
        printExpression(Expression.convertToPostfix(infixExpressionH));
        System.out.println();
        
        System.out.println("INFIX CONVERSION: Special Test Case #6");
        System.out.println("Checks when there are no operators present.");
        System.out.print("INFIX: ");
        String[] infixExpressionI = {"(","a",")"}; // infix expression (a)
        printExpression(infixExpressionI);
        printExpression(Expression.convertToPostfix(infixExpressionI));
        System.out.println("\n"); 
       
        System.out.println("POSTFIX EVALUATION: Test #1");
        System.out.println("a = 5, b = 3, c = 2");
        System.out.print("INFIX: ");
        printExpression(infixExpressionA);
        String[] postfixExpressionA = {"5","3","2","+","+"};
        System.out.print("POSTFIX: ");
        printExpression(postfixExpressionA);
        System.out.println("Evaluates to: " + Expression.evaluatePostfix(postfixExpressionA));                
        System.out.println();
        
        System.out.println("POSTFIX EVALUATION: Test #2");
        System.out.println("a = 6, b = 2, c = 8");
        System.out.print("INFIX: ");
        System.out.println("a+b*c");
        String[] postfixExpressionB = {"6","2","8","*","+"};
        System.out.print("POSTFIX: ");
        printExpression(postfixExpressionB);
        System.out.println("Evaluates to: " + Expression.evaluatePostfix(postfixExpressionB));                
        System.out.println();
         
        System.out.println("POSTFIX EVALUATION: Test #3");
        System.out.println("a = 6, b = 3, c = 9, d = 3");
        System.out.print("INFIX: ");
        printExpression(infixExpressionC);
        String[] postfixExpressionC = {"6","3","/","9","3","-","*"};
        System.out.print("POSTFIX: ");
        printExpression(postfixExpressionC);
        System.out.println("Evaluates to: " + Expression.evaluatePostfix(postfixExpressionC));                
        System.out.println();
        
        System.out.println("POSTFIX EVALUATION: Special Test Case #1");
        System.out.println("Checks when a zero is divided.");
        System.out.println("a = 4, b = 0");
        System.out.print("POSTFIX: ");
        String[] postfixExpressionD = {"4","0","/"};
        printExpression(postfixExpressionD);
        Expression.evaluatePostfix(postfixExpressionD);
        System.out.println();
        
    }
    
    /**
    * This method will print out the contents of the String array.
    * 
    * @param stringArray String array that will be printed out.
    */
    public static void printExpression(String[] stringArray)
    {
        if (stringArray == null)
        {
            System.out.println("null: This expression is not well formed!");
        }    
        else
        {
            for (int i = 0; i < stringArray.length; i++)
            {
                System.out.print(stringArray[i]);
            }
            System.out.println();
        }
    }
}  
