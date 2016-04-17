package de.phip1611.linked_lists.elements;

/**
 * Abstract representation of a List Element.
 */
public abstract class ListElement<T> {

    /**
     * The value of the List-Element. Can be everything you want!
     */
    protected T value;

    /**
     * Value of ListElement shall be set once at init.
     * @param value  content ListElement
     */
    public ListElement(T value) {
        this.value = value;
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

    /**
     * Set the Value of the ListElement
     * @param value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
