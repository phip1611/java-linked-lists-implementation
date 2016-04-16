package de.phip1611.linked_lists;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 *
 *
 * Abstract representation of a Linear List.
 * Monodirectional-Lists (not bidirectional) shall implement this.
 */
public abstract class LinearList<T> extends List<T> implements Iterator<T>, Iterable<T> {

    /**
     * You will need this to make Lists iterable and to find out at
     * which point of iteration you are right know.
     * RESERVED FOR JAVA-ITERATORS/FOREACH_LOOPS.
     *
     */
    protected Integer iterateProgressCount;

    /**
     * Always holds the element of the last iteration step to make for each loops possible.
     * RESERVED FOR JAVA-ITERATORS/FOREACH_LOOPS.
     */
    protected LinearListElement<T> iterateProgress;


    /**
     * Reference to the very first Element of the list/the beginning.
     * Overrides the Type from the upper clase List.
     */
    protected LinearListElement<T> listBegin;

    /**
     * Set-Up.
     */
    public LinearList() {
        super();
        this.iterateProgressCount = new Integer(0);
    }

    /**
     * Appends data to the List.
     * @param value
     */
    public abstract void append(T value) throws ListMaxSizeExceededException;

    /**
     * Inserts data to the List.
     * @param value
     */
    public abstract Boolean insert(Integer index, T value) throws ListMaxSizeExceededException;



    /**
     * Returns the last Element and deletes it.
     * For example if you wan't ro realize a stack
     * this shit is cool as fuck!
     * @return
     */
    public abstract T pop();

    /**
     * Returns content/value of a List at index.
     * @param index
     * @return
     */
    public abstract T getValue(Integer index);

    /**
     * Returns the index of the first
     * occurrence of the value in the List.
     * Returns 0 if the Element is not in the List.
     * @param value
     * @return
     */
    public abstract Integer getIndex(T value);

    /**
     * Returns an Array of index of all
     * occurrences of the value in the List.
     * @param value
     * @return
     */
    public abstract Integer[] getIndexes(T value);

    /**
     * Delets the List-Element at index.
     * Returns false if no element was deleted.
     * @param index
     */
    public abstract Boolean deleteAt(Integer index);

    /**
     * Deletes only the first occurrence of the value in the list.
     * Returns false if no element was deleted.
     * @param value
     */
    public abstract Boolean delete(T value);

    /**
     * Deletes all occurrences of the value in the list.
     * Returns false if no element was deleted.
     * @param value
     * @return
     */
    public abstract Boolean deleteAll(T value);


    /**
     * Prints the List to the console.
     */
    public void printList() {
        LinearListElement<T> current = this.listBegin;
        int i = List.LIST_BEGIN;
        if (current == null && elementCount == 0) {
            System.out.println("The List is empty!");
        }
        else if (current == null && elementCount != 0 || current != null && elementCount == 0) {
            System.err.println("printList() DEBUG: Reference to first Element is null but elementCount != null. Maybe a bug in the List-Class?");
        }
        else {
            while (current != null) {
                System.out.printf("Entrie %d: %s\n", i, current.toString());
                i++;
                current = current.getNext();
            }
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        this.iterateProgressCount = 0;
        this.iterateProgress = this.listBegin;
        return this;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public T next() {
        if (this.iterateProgress == null) {
            throw new NoSuchElementException("No further elements in the list.");
        } else {
            T returnThis = this.iterateProgress.getValue();
            this.iterateProgressCount++;
            this.iterateProgress = this.iterateProgress.getNext();
            return returnThis;
        }
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        // Leere Liste
        // Die Referenz auf das Folge-Element wird übrigens in next() umgeschrieben.
        return this.iterateProgress != null;
    }
}
