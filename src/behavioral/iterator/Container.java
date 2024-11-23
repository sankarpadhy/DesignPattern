package behavioral.iterator;

/**
 * Container interface for the Iterator pattern
 * Defines the contract for collections that can provide iterators
 * This interface follows the Collection framework style in Java
 */
public interface Container<T> {
    /**
     * Creates and returns an iterator for traversing the container's elements
     * @return Iterator instance for this container
     */
    Iterator<T> getIterator();
}
