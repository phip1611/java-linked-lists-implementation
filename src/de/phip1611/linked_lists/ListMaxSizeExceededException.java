package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 */
public class ListMaxSizeExceededException extends IndexOutOfBoundsException {
    public ListMaxSizeExceededException(String message) {
        super(message);
    }
    public ListMaxSizeExceededException() {
        super("");
    }
}
