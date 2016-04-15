package de.phip1611.linked_lists;

/**
 * Abstract representation of a Linear List Element
 */
public abstract class LinearListElement<T> extends ListElement<T> {

    private LinearListElement<T> next;

    /**
     * Value of ListElement shall be set once at init.
     *
     * @param value content ListElement
     */
    public LinearListElement(T value) {
        super(value);
    }

    /**
     * Sets the next Element of the List/the Element.
     * @param next
     */
    public void setNext(LinearListElement<T> next) {
        this.next = next;
    }

    /**
     * Get the next of the List/the Element.
     * @return
     */
    public LinearListElement<T> getNext() {
        return this.next;
    }
}
