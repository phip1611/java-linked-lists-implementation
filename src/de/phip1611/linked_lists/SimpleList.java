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
     * @param le
     */
    @Override
    public void append(LinearListElement<T> le) throws ListMaxSizeExceededException {
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
     * Appends data to the List.
     *
     * @param value
     */
    @Override
    public void append(T value) throws ListMaxSizeExceededException {
        this.append(new SimpleListElement<T>(value));
    }

    /**
     * Inserts data to the List. Index starts at 1.
     *
     * @param index 0-
     * @param le
     */
    @Override
    public boolean insert(Integer index, LinearListElement<T> le) throws ListMaxSizeExceededException {
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
     * Inserts data to the List. Index starts at 1.
     *
     * @param index
     * @param value
     */
    @Override
    public boolean insert(Integer index, T value) throws ListMaxSizeExceededException {
        return this.insert(index, new SimpleListElement<T>(value));
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
     * Returns Element of a List at index.
     *
     * @param index
     * @return
     */
    @Override
    public LinearListElement<T> getElement(Integer index) {
        SimpleListElement<T> listElement;
        Integer count = List.LIST_BEGIN;
        listElement = (SimpleListElement<T>) this.listBegin;
        while (listElement != null) {
            if (index.equals(count)) {
                return listElement;
            } else {
                listElement = (SimpleListElement<T>) listElement.getNext();
                count++;
            }
        }
        return null;
    }

    /**
     * Returns content/value of a List at index.
     *
     * @param index
     * @return
     */
    @Override
    public T get(Integer index) {
        SimpleListElement<T> returnThis = (SimpleListElement<T>) this.getElement(index);
        return (returnThis == null ? null : returnThis.getValue());
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
     * Determine if a specific ListElement is already in the List!
     *
     * @param le
     * @return
     */
    @Override
    public boolean isInList(ListElement le) {
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
     * Deletes all Elements in the List that matches the parameter.
     *
     * @param le
     * @return
     */
    @Override
    public boolean deleteAll(ListElement le) {
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
