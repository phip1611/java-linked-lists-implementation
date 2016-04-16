package de.phip1611.linked_lists;

/**
 * Created by phip1611 on 15.04.16.
 */
public class BidirectionalListElement<T> extends LinearListElement<T> {

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
}
