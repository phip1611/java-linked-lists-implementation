package de.phip1611.linked_lists;

/**
 * Will be thrown if the max size of a list
 * is exceeded.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 */
public class ListSizeExceededException extends RuntimeException {
    public ListSizeExceededException(String message) {
        super(message);
    }
    public ListSizeExceededException() {
        super("");
    }
}
