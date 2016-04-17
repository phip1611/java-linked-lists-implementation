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
        LinearListElement<T> le = new LinearListElement<>(value);
        // Liste ist noch leer
        if (this.elementCount == MAX_SIZE) {
            throw new ListMaxSizeExceededException();
        }
        if (this.listBegin == null) {
            this.listBegin = le;
        } else {
            LinearListElement current = (LinearListElement) this.listBegin;
            while (current.getNext() != null) {
                current = current.getNext();
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
    public Boolean insert(Integer index, T value) throws ListMaxSizeExceededException {
        LinearListElement<T> le = new LinearListElement<>(value);
        if (this.elementCount == MAX_SIZE) {
            throw new ListMaxSizeExceededException();
        }

        int count = LIST_BEGIN;

        if (index < LIST_BEGIN || index > this.elementCount) {
            return false;
        }
        if (this.listBegin == null && index == 1) {
            this.listBegin = le;
            return true;
        }
        if (index == LIST_BEGIN) {
            le.setNext(this.listBegin);
            this.listBegin = le;
            return true;
        } else {
            LinearListElement<T> previousElement, listElement;
            previousElement = null;
            listElement = this.listBegin;
            while (listElement != null) {
                if (index.equals(count)) {
                    previousElement.setNext(le);
                    le.setNext(listElement);
                    return true;
                }
                previousElement = listElement;
                listElement = listElement.getNext();
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
        LinearListElement<T> listElement, previousElement;
        listElement = this.listBegin;
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
                listElement = listElement.getNext();
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
    public T getValue(Integer index) {
        if (index < LIST_BEGIN || index > this.elementCount) {
            return null;
        }
        else {
            LinearListElement<T> listElement = this.listBegin;
            Integer count = LIST_BEGIN;
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
     * Returns the index of the first
     * occurrence of the value in the List.
     * Returns 0 if the Element is not in the List.
     * @param value
     * @return
     */
    @Override
    public Integer getIndex(T value) {
        LinearListElement<T> listElement = this.listBegin;
        Integer count = LIST_BEGIN;
        while (listElement != null) {
            if (listElement.getValue().equals(value)) {
                return count;
            }
            count++;
            listElement = listElement.getNext();
        }
        return 0;
    }

    /**
     * Returns an Array of index of all
     * occurrences of the value in the List.
     * @param value
     * @return
     */
    @Override
    public Integer[] getIndexes(T value) {
        return new Integer[0];
    }

    /**
     * Delets the List-Element at index.
     *
     * @param index
     */
    @Override
    public Boolean deleteAt(Integer index) {
        Integer count = LIST_BEGIN;

        if (index < LIST_BEGIN || index > this.elementCount) {
            return false;
        }
        if (this.listBegin == null) { // Liste leer
            return false;
        }
        if (index == LIST_BEGIN) {
            // Referenz auf Listenbeginn wird gelöscht
            // und durch Folgeelement ersetzt
            this.listBegin = this.listBegin.getNext();
            this.elementCount--;
            return true;
        }

        LinearListElement<T> listElement, previousElement;
        listElement = this.listBegin;
        previousElement = null;
        while (listElement != null) {
            if (count.equals(index)) {
                if (previousElement.getNext() != null) {
                    previousElement.setNext(listElement.getNext());
                    listElement = null;
                    this.elementCount--;
                    return true;
                }
            }
            previousElement = listElement;
            listElement = listElement.getNext();
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
    public Boolean delete(T value) {
        if (!isInList(value)) {
            return false;
        }
        return deleteAt(getIndex(value));
    }

    /**
     * Deletes all occurrences of the value in the list.
     *
     * @param value
     * @return
     */
    @Override
    public Boolean deleteAll(T value) {
        if (!isInList(value)) {
            return false;
        }
        else {
            while(isInList(value)) {
                deleteAt(getIndex(value));
            }
            return true;
        }
    }



    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     *
     * @param value
     * @return
     */
    @Override
    public Boolean isInList(T value) {
        LinearListElement<T> listElement = this.listBegin;
        while (listElement != null) {
            if (listElement.getValue().equals(value)) {
                return true;
            }
            listElement = listElement.getNext();
        }
        return false;
    }

    /**
     * Brings List into an String-readable representation.
     *
     * @return
     */
    @Override
    public String toString() {
        return "You just tried to print a SimpleList with "+this.elementCount+" Elements.";
    }



}
