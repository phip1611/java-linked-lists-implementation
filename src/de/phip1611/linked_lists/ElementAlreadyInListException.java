package de.phip1611.linked_lists;

/**
 * Created by phip1611 on 20.04.16.
 */
public class ElementAlreadyInListException extends RuntimeException {
    public ElementAlreadyInListException(String m) {
        super(m);
    }public ElementAlreadyInListException() {
        super();
    }
}
