package de.phip1611.linked_lists;

/**
 * Will be thrown if the index is out of bounds.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 */
public class ListIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public ListIndexOutOfBoundsException(String message) {
        super(message);
    }
    public ListIndexOutOfBoundsException() {
        super("");
    }
}
