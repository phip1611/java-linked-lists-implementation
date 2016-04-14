package de.phip1611.linked_lists;

/**
 * Created by phip1611 on 14.04.16.
 */
public abstract class ListElement<T> {

    /**
     * The value of the List-Element. Can be everything you want!
     */
    private T value;

    /**
     * Referenz auf den Listenanfang.
     */
    private ListElement firstElement;

    /**
     * Value of ListElement shall be set once at init.
     * @param value  content ListElement
     */
    public ListElement(T value) {
        this.value = value;
        this.firstElement = null;
    }

    /**
     * List-Element String representation.
     * @return
     */
    public String toString() {
        return String.valueOf(this.value);
    }

    /**
     * Get the value of the ListElement.
     * @return
     */
    public T getValue() {
        return value;
    }
}
