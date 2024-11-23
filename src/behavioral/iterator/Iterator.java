package behavioral.iterator;

/**
 * Iterator interface defining the standard iteration operations
 * This interface provides methods for traversing elements in a collection
 * @param <T> Type of elements being iterated
 */
public interface Iterator<T> {
    /**
     * Checks if there are more elements to iterate
     * @return true if there are more elements, false otherwise
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration
     * @return the next element of type T
     */
    T next();

    /**
     * Resets the iterator to the beginning of the collection
     */
    void reset();
}
