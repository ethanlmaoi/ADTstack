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
 * Expression is a class for the properties of both postfix and infix expressions
 * Allows the conversion of an infix expression to postfix expression.
 * Allows the evaluation of a postfix expression which returns an integer
 * representing the result of the evaluation.
 * Expression uses stacks (with implementation of arrays( to help with the conversion and evaluation.
 * There are no instance variables, only non-static methods that assist the two
 * class methods: convertToPostfix and evaluatePostfix
 * 
 * @author Ethan Liao
 */
public class Expression 
{
    /** 
    * Class default constructor that is set private instead of public.
    * This is so that an instance of this class can't be constructed in the main or
    * in any other classes.
    */
    private Expression()
    {
        // private default constructor
    }
    
    /** 
    * This class method converts an infix expression to a postfix expression.
    * Will take in a String array representing an infix expression.
    * Returns a String array representing the converted postfix expression.
    * 
    * @param infixExpression a String array representing the infix expression
    * @return postfixExpression a String array representing the postfix expression
    */
    public static String[] convertToPostfix(String[] infixExpression)
    {
        
        Expression e = new Expression(); // gives access to non-static methods in this class
        
        if (!e.checkWellFormed(infixExpression)) // checks if well formed
        {
            return null;    
        }
        
        StackInterface<String> operatorStack = new ArrayStack<String>(100);
        String postfix = "";
        
        for (int i = 0; i < infixExpression.length; i++)
        {
            String token =  infixExpression[i];
            if (e.isVariable(token) || e.isNumber(token))
            {
                postfix+=token;
            }
            else if (e.isOperator(token))
            {
                while (!operatorStack.isEmpty() && (e.prescedence(token) <= e.prescedence(operatorStack.peek())))
                {
                    postfix+=operatorStack.peek();
                    operatorStack.pop();
                }
                operatorStack.push(token);
            }
            else if (token == "(")
            {
                operatorStack.push(token);
            }
            else if (token == ")")
            {
                String topOperator = operatorStack.pop();
                while (topOperator != "(")
                {
                    postfix+=topOperator;
                    topOperator = operatorStack.pop();
                }
            }
        }    
        while (!operatorStack.isEmpty())
        {
            String topOperator = operatorStack.pop();
            postfix+=topOperator;
        }
        return e.convertToStringArray(postfix);
    }
    
    /** 
    * This class method evaluates a postfix expression.
    * Will take in a String array representing an postfix expression.
    * Returns an integer which is the result of the evaluation.
    * 
    * @param postfixExpression a String array representing the postfix expression
    * @return int Integer representing the result of the evaluation
    */
    public static int evaluatePostfix(String[] postfixExpression)
    {
        Expression e = new Expression(); // gives access to non-static methods in this class
        
        StackInterface<String> valueStack = new ArrayStack<String>(100);
        for (int i = 0; i < postfixExpression.length; i++)
        {
            String nextCharacter = postfixExpression[i];
            if (e.isNumber(nextCharacter))
            {
                valueStack.push(nextCharacter);
            }
            else if (e.isOperator(nextCharacter))
            {                
                int operandTwo = Integer.parseInt(valueStack.pop());
                int operandOne = Integer.parseInt(valueStack.pop());
                
                int result = 0;
                if ("+".equals(nextCharacter))
                {
                    result = operandOne + operandTwo;
                }
                else if ("-".equals(nextCharacter))
                {
                    result = operandOne - operandTwo;
                }
                else if ("*".equals(nextCharacter))
                {
                    result = operandOne * operandTwo;
                }
                else if ("/".equals(nextCharacter))
                {
                    try
                    {
                        result = operandOne / operandTwo;
                    }
                    catch (ArithmeticException e2)
                    {
                        System.out.println("Number cannot be divided by zero!");
                    }
                }
                valueStack.push(Integer.toString(result));
            }
        }
        return Integer.parseInt(valueStack.peek());
    }
    
    /** 
    * This method will check if an expression is well formed and suitable for
    * infix to postfix conversion. Checks the following:
    * 1) Checks if variables are present.
    * 2) Less than 3 variables and operators combined
    * 3) If two operators are next to each other
    * 4) If two variables are next to each other
    * 5) Unbalanced number of right and left parentheses
    * Will take in a String array representing an postfix expression.
    * Returns a boolean that specifies if the expression is well formed.
    * 
    * @param stringArray a String array representing the postfix expression
    * @return boolean True if expression is well formed, false otherwise
    */
    public boolean checkWellFormed(String[] stringArray)
    {
        // can't have an expression with no variables or numbers
        boolean variable = false;
        for (int i = 0; i < stringArray.length; i++)
        {
            if (isVariable(stringArray[i]) || isNumber(stringArray[i]))
            {
                variable = true;
            }
        }
        if (!variable)
        {
            return false;
        }
        
        // can't have an expression with no operators
        boolean operator = false;
        for (int i = 0; i < stringArray.length; i++)
        {
            if (isOperator(stringArray[i]))
            {
                operator = true;
            }
        }
        if (!operator)
        {
            return false;
        }

        // can't have an expression with less than 3 variables/operators
        if (stringArray.length < 3)
        {
            return false;
        }
        
        // can't have two operators or two variables or next to each other
        int j = 1;
        while (j < stringArray.length)
        {
            String first = stringArray[j-1];
            String second = stringArray[j];
            if ("(".equals(first) || "(".equals(second) || ")".equals(first) || ")".equals(second))
            {
                j++;
            }
            else
            {
                if (isOperator(first) == isOperator(second))
                {
                    return false;
                }
                if (isOperand(first) == isOperand(second))
                {
                    return false;
                }
                j++;
            }
        }
        
        // can't have an expression with uneven number of right and left parentheses
        int leftCounter = 0;
        int rightCounter = 0;
        
        for (int i = 0; i < stringArray.length; i++)
        {
            if (stringArray[i] == "(")
            {
                leftCounter++;
            }
            if (stringArray[i] == ")")
            {
                rightCounter++;
            }
        }
        return leftCounter == rightCounter;
    }
    
    private boolean isOperand(String s)
    {
        if (Character.isLetter(s.charAt(0)))
        {
            return true;
        }
        try
        {
            Integer.parseInt(s);
            return true;
        } 
        catch (NumberFormatException ex)
        {
            // do nothing
        }       
        return false;
    }
    
    /** 
    * This method checks if a String is a number.
    * Will take in a String.
    * Returns a boolean that specifies whether or not the String was a number.
    * 
    * @param s String
    * @return boolean True if s is a number, false otherwise
    */
    private boolean isNumber(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } 
        catch (NumberFormatException ex)
        {
            return false;
        }
    }     
    
    /** 
    * This method checks if a String is a variable.
    * Will take in a String.
    * Returns a boolean that specifies whether or not the String was a variable.
    * 
    * @param s String
    * @return boolean True if s is a variable, false otherwise
    */
    private boolean isVariable(String s)
    {
        if (Character.isLetter(s.charAt(0)))
        {
            return true;
        }
        return false;
    }
    
    /** 
    * This method checks if a String is an operator (+,-,*,/)
    * Will take in a String.
    * Returns a boolean that specifies whether or not the String was an operator.
    * 
    * @param s String
    * @return boolean True if s is an operator, false otherwise
    */
    private boolean isOperator(String s)
    {
        return ("+".equals(s)) || ("-".equals(s)) || ("*".equals(s)) || ("/".equals(s));
    }
    
    /** 
    * This method will take in an operator and return its prescedence as an integer.
    * The higher the number is, the more "important" it is.
    * Will take in a String.
    * Returns an integer that represents the operator's prescedence.
    * 
    * @param s String (operator)
    * @return int Representing the operator's prescedence
    */
    private int prescedence(String s)
    {
        if (s == "*" || s == "/")
        {
            return 2;
        }
        if (s == "+" || s == "-")
        {
            return 1;
        }
        return 0;
    }
    
    /** 
    * This method converts a String to an Array of Strings.
    * Each cell will have a single char, but will be in a String format.
    * Will take in a String.
    * Returns a String array that represents the inputted String.
    * 
    * @param s String that will be converted
    * @return String[] Result of the conversion of String s
    */
    private String[] convertToStringArray(String s)
    {
        String[] stringArray = new String[s.length()];
        for (int i = 0; i < stringArray.length; i++)
        {
            stringArray[i] = Character.toString(s.charAt(i));
        }
        return stringArray;
    }
}