package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 *
 *
 * A implementation of a simple linear list!
 * This implementation is slow due to it only saves
 * the list begin and it's just one-directional/mono-directional.
 * You should just learn from it. Rather use BidirectionalList!
 *
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
    public void append(T value) throws ListSizeExceededException {
        // Elemente dürfen nur einmalig hinzugefügt werden
        if (this.strictMode) {
            if (isInList(value)) {
                System.err.println(value + "ist bereits in der Liste!");
                throw new ElementAlreadyInListException(
                        "Element "+value.toString()+"ist bereits in der Liste. Strict Mode aktiv!");
            }
        }


        LinearListElement<T> le = new LinearListElement<>(value);
        // Liste ist noch leer
        if (this.elementCount == MAX_SIZE) {
            throw new ListSizeExceededException();
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
    public Boolean insert(Integer index, T value) throws ListSizeExceededException {
        LinearListElement<T> le = new LinearListElement<>(value);
        if (this.elementCount == MAX_SIZE) {
            throw new ListSizeExceededException();
        }
        int count = LIST_BEGIN;
        // Throws an Exception if is not in Range
        indexInRange(index);

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
            this.elementCount--;
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
        // Throws an Exception if is not in Range
        if (indexInRange(index)) {
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
        }
        return null; // this should never be reached, just fallback
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
    public Boolean deleteAt(Integer index) throws ListSizeExceededException {
        Integer count = LIST_BEGIN;

        // Throws an Exception if not.
        indexInRange(index);

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
        Integer count = LIST_BEGIN;
        if (this.listBegin.getValue().equals(value)) {
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
            if (listElement.getValue().equals(value)) {
                if (listElement.hasNext()) {
                    previousElement.setNext(listElement.getNext());
                }
                listElement = null;
                this.elementCount--;
                return true;
            } else {
                previousElement = listElement;
                listElement = listElement.getNext();
                count++;
            }
        }
        return false;
    }

    /**
     * Deletes all occurrences of the value in the list.
     *
     * @param value
     * @return
     */
    @Override
    public Boolean deleteAll(T value) {
        Integer count = LIST_BEGIN;
        Boolean deletedAny = false;
        if (this.listBegin == null) {
            // leere Lists
            return false;
        } else if (this.elementCount == 1) {
            // ein-elementige Liste
            if (this.listBegin.getValue().equals(value)) {
                this.listBegin = null;
                this.elementCount--;
                return true;
            }
        }

        LinearListElement<T> listElement, previousElement;
        listElement = this.listBegin;
        previousElement = null;
        while (listElement != null) {
            if (listElement.getValue().equals(value)) {
                // aktuelels Listenelement hat den gewünschten Inhalt
                if (this.listBegin == listElement) {
                    this.listBegin = listElement = this.listBegin.getNext();
                    this.elementCount--;
                    deletedAny = true;
                    continue;
                }
                else if (!listElement.hasNext()) {
                    // ListenENDE
                    previousElement.setNext(null);
                    listElement = null;
                    this.elementCount--;
                    return true;
                }
                // normales weiter iterieren
                if ((previousElement != null && listElement.hasNext())) {
                        previousElement.setNext(listElement.getNext());
                }
                listElement = listElement.getNext();
                this.elementCount--;
                deletedAny = true;
            } else {
                previousElement = listElement;
                listElement = listElement.getNext();
                count++;
            }
        }
        return deletedAny;
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
