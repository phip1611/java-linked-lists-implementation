package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 *
 *
 * A implementation of a SimpleList that is awesome!
 */
public class SimpleList<T> extends LinearList<T> {

    public SimpleList() {
        super();
    }

    /**
     * Appends data to the List.
     *
     * @param value
     */
    @Override
    public void append(T value) throws ListMaxSizeExceededException {
        SimpleListElement<T> le = new SimpleListElement<T>(value);
        // Liste ist noch leer
        if (this.elementCount == List.MAX_SIZE) {
            throw new ListMaxSizeExceededException();
        }
        if (this.listBegin == null) {
            this.listBegin = le;
        } else {
            SimpleListElement current = (SimpleListElement) this.listBegin;
            while (current.getNext() != null) {
                current = (SimpleListElement) current.getNext();
            }
            current.setNext(le);
        }
        this.elementCount++;
    }

    /**
     * Inserts data to the List. Index starts at 1.
     *
     * @param index
     * @param value
     */
    @Override
    public boolean insert(Integer index, T value) throws ListMaxSizeExceededException {
        SimpleListElement<T> le = new SimpleListElement<T>(value);
        if (this.elementCount == List.MAX_SIZE) {
            throw new ListMaxSizeExceededException();
        }

        int count = List.LIST_BEGIN;

        if (index < List.LIST_BEGIN || index > this.elementCount) {
            return false;
        }
        if (this.listBegin == null && index == 1) {
            this.listBegin = le;
            return true;
        }
        if (index == List.LIST_BEGIN) {
            le.setNext(this.listBegin);
            this.listBegin = le;
            return true;
        } else {
            SimpleListElement<T> previousElement, listElement;
            previousElement = null;
            listElement = (SimpleListElement<T>) this.listBegin;
            while (listElement != null) {
                if (index.equals(count)) {
                    previousElement.setNext(le);
                    le.setNext(listElement);
                    return true;
                }
                previousElement = listElement;
                listElement = (SimpleListElement<T>) listElement.getNext();
                count++;
            }
        }
        return false; // this point should never be reached but its a fallback
    }


    /**
     * Returns the last Elements value and deletes from the list it.
     * For example if you wan't ro realize a stack
     * this shit is cool as fuck!
     *
     * @return
     */
    @Override
    public T pop() {
        SimpleListElement<T> listElement, previousElement;
        listElement = (SimpleListElement<T>) this.listBegin;
        previousElement = null;

        if (listElement == null) { // leere Liste
            return null;
        } else if (this.elementCount == 1) {
            T returnThis = listElement.getValue();
            this.listBegin = null;
            return returnThis;
        } else {
            while (listElement.hasNext()) {
                previousElement = listElement;
                listElement = (SimpleListElement<T>) listElement.getNext();
            }
            previousElement.setNext(null);
            this.elementCount--;
            return listElement.getValue();
        }
    }

    /**
     * Returns content/value of a List at index.
     *
     * @param index
     * @return
     */
    @Override
    public T get(Integer index) {
        if (index < List.LIST_BEGIN || index > this.elementCount) {
            return null;
        }
        else {
            LinearListElement<T> listElement = this.listBegin;
            Integer count = List.LIST_BEGIN;
            while (listElement != null) {
                if (count.equals(index)) {
                    return listElement.getValue();
                } else {
                    listElement = listElement.getNext();
                    count++;
                }
            }
            return null; // this should never be reached, just fallback
        }
    }

    /**
     * Delets the List-Element at index.
     *
     * @param index
     */
    @Override
    public boolean delete(Integer index) {
        Integer count = List.LIST_BEGIN;

        if (index < List.LIST_BEGIN || index > this.elementCount) {
            return false;
        }
        if (this.listBegin == null) { // Liste leer
            return false;
        }
        if (index == List.LIST_BEGIN) {
            this.listBegin = this.listBegin.getNext();
            return true;
        }

        SimpleListElement<T> listElement, previousElement;
        listElement = (SimpleListElement<T>) this.listBegin;
        previousElement = null;
        while (listElement != null) {
            if (count.equals(index)) {
                if (previousElement.getNext() != null) {
                    previousElement.setNext(listElement.getNext());
                    listElement = null;
                    return true;
                }
            }
            previousElement = listElement;
            listElement = (SimpleListElement<T>) listElement.getNext();
            count++;
        }
        return false;
    }

    /**
     * Deletes only the first occurrence of the value in the list.
     *
     * @param value
     */
    @Override
    public boolean delete(T value) {
        return false;
    }

    /**
     * Deletes all occurrences of the value in the list.
     *
     * @param value
     * @return
     */
    @Override
    public boolean deleteAll(T value) {
        return false;
    }

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     *
     * @param value
     * @return
     */
    @Override
    public boolean isInList(T value) {
        return false;
    }

    /**
     * Brings List into an String-readable representation.
     *
     * @return
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * Clears a data structure / wipe's the fuck out of it.
     */
    @Override
    public void clear() {
        this.listBegin = null;
        this.elementCount = null;
        // Garbage Collection should kill all the elements
        // cause they never will be used again
    }



}
