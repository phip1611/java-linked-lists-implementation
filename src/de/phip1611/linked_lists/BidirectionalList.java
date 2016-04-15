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

    /**
     * Appends data to the List.
     *
     * @param le
     */
    @Override
    public void append(LinearListElement<T> le) {

    }

    /**
     * Appends data to the List.
     *
     * @param value
     */
    @Override
    public void append(T value) {

    }

    /**
     * Inserts data to the List.
     *
     * @param index
     * @param le
     */
    @Override
    public void insert(Integer index, LinearListElement<T> le) {

    }

    /**
     * Inserts data to the List.
     *
     * @param index
     * @param value
     */
    @Override
    public void insert(Integer index, T value) {

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
     * Returns Element of a List at index.
     *
     * @param index
     * @return
     */
    @Override
    public T getElement(Integer index) {
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
    public void delete(Integer index) {

    }

    /**
     * Determine if a specific ListElement is already in the List!
     *
     * @param le
     * @return
     */
    @Override
    public boolean isInList(ListElement le) {
        return false;
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
     * Deletes all Elements in the List that matches the parameter.
     *
     * @param le
     * @return
     */
    @Override
    public boolean deleteAll(ListElement le) {
        return false;
    }

    /**
     * Deletes all Elements in the List that matches the parameter.
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

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return false;
    }
}
