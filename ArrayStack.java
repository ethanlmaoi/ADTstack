/**
 * Name: Liao, Ethan
 * Assignment: Project #3
 * Due: 5/25/2017
 * Course: cs-240-02
 * Description: This project requires me to implement two class methods that can
 * convert an infix expression to its equivalent postfix and evaluate the postfix
 * expression.
 */

import java.util.EmptyStackException;

/**
 * ArrayStack is a generic class the implements the generic interface Stack.
 * It is a stack (data structure) that uses arrays (implementation).
 * Holds three instance variables:
 * size - integer that represents the size of the array stack
 * top - integer that represents the location of the top entry
 * elements - array of entries
 * 
 * @author Ethan Liao
 */
public class ArrayStack<T> implements StackInterface<T>
{
    private int size;
    private int top;
    private T[] elements;
    
    /** 
    * Class default constructor that represents an array stack with
    * starting capacity of 10.
    */
    public ArrayStack()
    {
        this(10);
    }
    
    /** 
    * Class constructor that represents an array stack that takes in a
    * integer as a parameter that will specify the size of the stack.
    * 
    * @param int Size of the stack.
    */
    public ArrayStack(int s)
    {
        elements = (T[]) new Object[s];
        top = -1;
        size = s;
    }
    
    /**
     * Adds the given item to the top of the stack.
     * 
     * @param T An element that will be pushed onto the top of the stack
     */
    public void push(T newEntry)
    {
        elements[top+1] = newEntry;
        top++;
    }
    
    /**
     * Removes the top item from the stack and returns it.
     * 
     * @return T An element that is popped up from the stack
     */
    public T pop()
    {
        T entry = peek();
        top--;
        return entry;
    }
    
    /**
     * Returns the top item from the stack without popping it.
     * 
     * @return T An element that the top item of the stack
     */
    public T peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return elements[top];
        }
    }
    
    /**
     * Clears the stack.
     */
    public void clear()
    {
        elements = (T[]) new Object[size];
        top = -1;
    }
    
    /**
     * Returns whether the stack is empty or not.
     * 
     * @return boolean True if the stack is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return top == -1;
    }
}