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
     * Max count of Elements a list can hold.
     */
    public static final int MAX_SIZE = Integer.MAX_VALUE;

    /**
     * Set strictMode to this if it should not be allowed to add several items more than once.
     */
    public static final boolean STRICT_MODE = true;

    /**
     * Set strictMode to this if it should not be allowed to add several items more than once.
     */
    public static final boolean SLACK_MODE = false;

    /**
     * DEFAULT-Value for this.strictMode
     */
    public static final boolean DEFAULT_MODE = List.SLACK_MODE;

    /**
     * Whenever you want to insert or remove an Element
     * this is the base value for the index. For example causes
     * insert(List.LIST_BEGIN, ...) on an empty List
     * that the first ListElement will be created.
     */
    public static final int LIST_BEGIN = 1;

    /**
     * The internal Counter for Elements in the List.
     */
    protected int elementCount;

    /**
     * Reference to the very first Element of the list/the beginning.
     */
    protected ListElement<T> listBegin;

    /**
     * If strictMode is true then it's not allowed to insert
     * Elements into the List that are already in! Otherwise
     * you can add items multiple.
     */
    protected boolean strictMode;


    /**
     * Set-Up the list.
     */
    public List() {
        this.listBegin = null;
        this.elementCount = 0;
        this.strictMode = List.DEFAULT_MODE;
    }

    /**
     * Clears a data structure / wipe's the fuck out of it.
     */
    public void clear() {
        this.listBegin = null;
        this.elementCount = 0;
        // Garbage Collection should kill all the lists
        // cause they never will be used again
    }

    /**
     * If you want to set the mode you can use as parameters:
     * <li>List.SLACK_MODe</li>
     * <li>List.STRICT_MODE</li>
     * @param mode
     */
    public void setMode(Boolean mode) {
        this.strictMode = mode;
    }

    public boolean getMode() {
        return this.strictMode;
    }

    /**
     * Get the Number of Elements in the List!
     * @return
     */
    public int getElementCount() {
        return this.elementCount;
    }

    /**
     * @Alias this.getElementCount()
     * @return
     */
    public int size() {
        return this.elementCount;
    }

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     * @param value
     * @return
     */
    public abstract boolean isInList(T value);

    /**
     * Deletes all Elements in the List that matches the parameter.
     * Returns false if no element was deleted.
     * @param value
     * @return
     */
    public abstract boolean removeAll(T value);

    /**
     * Brings List into an String-readable representation.
     * @return
     */
    public abstract String toString();

}