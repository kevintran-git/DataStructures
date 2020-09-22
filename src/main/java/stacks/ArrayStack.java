package stacks;

import java.util.Arrays;

public class ArrayStack<T> implements StackInterface<T> {
    @SuppressWarnings("unchecked")
    private T[] stack = (T[]) new Object[2];
    private int topIndex = -1;

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws NullPointerException if the stack is empty before the operation.
     */
    @Override
    public T pop() {
        if (isEmpty()) throw new NullPointerException("Stack is empty.");
        T top = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return top;
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws NullPointerException if the stack is empty.
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new NullPointerException("Stack is empty.");
        return stack[topIndex];
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        stack = (T[]) new Object[2];
    }

    /**
     * Doubles the array's capacity if it's full.
     */
    private void ensureCapacity() {
        if (topIndex >= stack.length - 1)
            stack = Arrays.copyOf(stack, 2 * stack.length); // If array is full, double its size
    }

}
