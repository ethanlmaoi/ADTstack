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
 * This interface has all of the requirements needed for a generic stack.
 * A Stack MUST have these three methods: push(), pop(), and peek().
 * This interface has an additional two method: clear() and isEmpty().
 * 
 * @author Ethan Liao
 */
public interface StackInterface<T>
{
    /**
     * Adds the given item to the top of the stack.
     * 
     * @param T An element that will be pushed onto the top of the stack
     */
    void push(T newEntry);

    /**
     * Removes the top item from the stack and returns it.
     * 
     * @return T An element that is popped up from the stack
     */
    T pop();

    /**
     * Returns the top item from the stack without popping it.
     * 
     * @return T An element that the top item of the stack
     */
    T peek();

    /**
     * Clears the stack.
     */
    void clear();

    /**
     * Returns whether the stack is empty or not.
     * 
     * @return boolean True if the stack is empty, false otherwise
     */
    boolean isEmpty();
}