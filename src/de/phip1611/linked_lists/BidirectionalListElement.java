package de.phip1611.linked_lists;

/**
 * Created by phip1611 on 15.04.16.
 */
public class BidirectionalListElement<T> extends LinearListElement<T> {

    //@Override because of Type!
    private BidirectionalListElement<T> next;

    private BidirectionalListElement<T> previous;

    /**
     * Value of ListElement shall be set once at init.
     *
     * @param value content ListElement
     */
    public BidirectionalListElement(T value) {
        super(value);
    }

    /**
     * Set the previous Element of a List-Element
     * @param previous
     */
    public void setPrevious(BidirectionalListElement<T> previous) {
        this.previous = previous;
    }

    /**
     * Get the previous Element of a List-Element
     * @return
     */
    public BidirectionalListElement<T> getPrevious() {
        return this.previous;
    }


    /**
     * Sets the next Element of the List/the Element.
     * @param next
     */
    //@Override no override because other type (overloaded method)
    public void setNext(BidirectionalListElement<T> next) {
        this.next = next;
    }

    /**
     * Get the next of the List/the Element.
     * @return
     */
    @Override
    public BidirectionalListElement<T> getNext() {
        return this.next;
    }

    /**
     * Returns true if there is a next element.
     * @return
     */
    public boolean hasPrevious() {
        return (this.previous != null);
    }

    /**
     * Determine whethere there is a next Element or not!
     * This must be overwritten because otherwise there is a bug where this
     * is always null!
     * @return
     */
    @Override
    public boolean hasNext() {
        return (this.next != null);
    }
}
