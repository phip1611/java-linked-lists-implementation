package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 *
 *
 * A list where each List Element knows what's the
 * next and whats the previous Element.
 */
public class BidirectionalList<T> extends LinearList<T> {

    public BidirectionalList() {
        throw new UnsupportedOperationException("To be implemented.");
    }

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     *
     * @param value
     * @return
     */
    @Override
    public boolean isInList(T value) {
        return false;
    }

    /**
     * Appends data to the List.
     *
     * @param value
     */
    @Override
    public void append(T value) throws ListMaxSizeExceededException {

    }

    /**
     * Inserts data to the List.
     *
     * @param index
     * @param value
     */
    @Override
    public boolean insert(Integer index, T value) throws ListMaxSizeExceededException {
        return false;
    }

    /**
     * Returns the last Element and deletes it.
     * For example if you wan't ro realize a stack
     * this shit is cool as fuck!
     *
     * @return
     */
    @Override
    public T pop() {
        return null;
    }

    /**
     * Returns content/value of a List at index.
     *
     * @param index
     * @return
     */
    @Override
    public T get(Integer index) {
        return null;
    }

    /**
     * Delets the List-Element at index.
     *
     * @param index
     */
    @Override
    public boolean delete(Integer index) {
        return false;
    }

    /**
     * Deletes only the first occurrence of the value in the list.
     *
     * @param value
     */
    @Override
    public boolean delete(T value) {
        return false;
    }

    /**
     * Deletes all occurrences of the value in the list.
     *
     * @param value
     * @return
     */
    @Override
    public boolean deleteAll(T value) {
        return false;
    }

    /**
     * Brings List into an String-readable representation.
     *
     * @return
     */
    @Override
    public String toString() {
        return null;
    }


    /**
     * Clears a data structure / wipe's the fuck out of it.
     */
    @Override
    public void clear() {

    }
}
