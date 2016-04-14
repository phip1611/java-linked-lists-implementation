package de.phip1611.linked_lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by phip1611 on 14.04.16.
 */
public class SimpleList<T> extends LinearList<T> {


    /**
     * Get the Number of Elements in the List!
     *
     * @return
     */
    @Override
    public int getElementsCount() {
        return 0;
    }

    /**
     * Determine if a specific ListElement is already in the List!
     *
     * @param le
     * @return
     */
    @Override
    public boolean find(ListElement le) {
        return false;
    }

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     *
     * @param value
     * @return
     */
    @Override
    public boolean find(T value) {
        return false;
    }

    /**
     * Clears a list / wipe's the fuck out of all elements.
     */
    @Override
    public void clear() {

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
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ListElement> iterator() {
        return null;
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

    /**
     * Returns the next element in the iteration.
     *
     * @retur‚n the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public ListElement next() {
        return null;
    }

    @Override
    public void append(ListElement<T> le) {

    }

    @Override
    public ListElement<T> pop(ListElement<T> le) {
        return null;
    }

    @Override
    public void delete(Integer index) {

    }
}
