package de.phip1611.linked_lists;

import java.util.Iterator;

/**
 * Created by phip1611 on 14.04.16.
 */
public abstract class List<T> implements Iterator<ListElement>, Iterable<ListElement> {
    /**
     * You will need this to make Lists iterable and to find out at
     * which point of iteration you are right know.
     */
    private Integer iterateCount;

    /**
     * Reference to the very first Element of the list/the beginning.
     */
    private ListElement<T> firstElement;

    /**
     * Alle List-Elemente
     */
    private ListElement<T>[] listElements;

    /**
     * Get the Number of Elements in the List!
     * @return
     */
    public abstract int getElementsCount();

    /**
     * Determine if a specific ListElement is already in the List!
     * @param le
     * @return
     */
    public abstract boolean find(ListElement le);

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     * @param value
     * @return
     */
    public abstract boolean find(T value);

    /**
     * Clears a list / wipe's the fuck out of all elements.
     */
    public abstract void clear();

    /**
     * Deletes all Elements in the List that matches the parameter.
     * @param le
     * @return
     */
    public abstract boolean deleteAll(ListElement le);

    /**
     * Deletes all Elements in the List that matches the parameter.
     * @param value
     * @return
     */
    public abstract boolean deleteAll(T value);

    /**
     * Brings List into an String-readable representation.
     * @return
     */
    public abstract String toString();

    /**
     * Set-Up the list.
     */
    public List() {
        this.iterateCount = new Integer(0);
        this.firstElement = null;
        this.listElements = null;
    }
}