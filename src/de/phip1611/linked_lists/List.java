package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 *
 *
 * Abstract representation of a List.
 */
public abstract class List<T> implements Clearable {

    /**
     * The internal Counter for Elements in the List.
     */
    protected Integer elementsCount;

    /**
     * Reference to the very first Element of the list/the beginning.
     */
    protected ListElement<T> listBegin;

    /**
     * Get the Number of Elements in the List!
     * @return
     */
    public int getElementsCount() {
        return this.elementsCount;
    }

    /**
     * Determine if a specific ListElement is already in the List!
     * @param le
     * @return
     */
    public abstract boolean isInList(ListElement le);

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     * @param value
     * @return
     */
    public abstract boolean isInList(T value);

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
        this.listBegin = null;
        this.elementsCount = 0;
    }
}