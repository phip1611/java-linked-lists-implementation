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
     */
    protected Integer iterateProgressCount;

    /**
     * Always holds the element of the last iteration step to make for each loops possible.
     */
    protected LinearListElement<T> iterateProgress;

    /**
     * Appends data to the List.
     * @param le
     */
    public abstract void append(LinearListElement<T> le);

    /**
     * Appends data to the List.
     * @param value
     */
    public abstract void append(T value);

    /**
     * Inserts data to the List.
     * @param le
     */
    public abstract void insert(Integer index, LinearListElement<T> le);

    /**
     * Inserts data to the List.
     * @param value
     */
    public abstract void insert(Integer index, T value);

    /**
     * Returns the last Element and deletes it.
     * For example if you wan't ro realize a stack
     * this shit is cool as fuck!
     * @return
     */
    public abstract T pop();

    /**
     * Returns Element of a List at index.
     * @param index
     * @return
     */
    public abstract T getElement(Integer index);

    /**
     * Returns content/value of a List at index.
     * @param index
     * @return
     */
    public abstract T get(Integer index);

    /**
     * Delets the List-Element at index.
     * @param index
     */
    public abstract void delete(Integer index);

    /**
     * Prints the List to the console.
     */
    public void printList() {
        LinearListElement<T> current = (LinearListElement<T>) this.listBegin;
        int i = 0;
        if (current == null) {
            System.out.println("The List is empty!");
        } else {
            while (current != null) {
                System.out.printf("Entrie %d: %s\n", i, current.toString());
                i++;
                current = current.getNext();
            }
        }
    }

    /**
     * Set-Up.
     */
    public LinearList() {
        super();
        this.iterateProgressCount = new Integer(0);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        this.iterateProgressCount = 0;
        this.iterateProgress = (LinearListElement<T>) this.listBegin;
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
        if (this.iterateProgress == null) {
            return false;
        } else {
            return true;
        }
    }
}
