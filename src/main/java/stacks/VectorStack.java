package stacks;

import java.util.Vector;

public class VectorStack<T> implements StackInterface<T> {
    Vector<T> stack = new Vector<>(); //Java supports dynamically sized vectors.
    //This vector will get longer as the size increases.

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        stack.add(newEntry);
    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws NullPointerException if the stack is empty before the operation.
     */
    @Override
    public T pop() {
        return stack.remove(stack.size() - 1);
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws NullPointerException if the stack is empty.
     */
    @Override
    public T peek() {
        return stack.lastElement();
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    public void clear() {
        stack.clear();
    }
}
