package de.phip1611.linked_lists;

/**
 * This is part of my Lists-Implementation.
 *
 * @author Philipp Schuster | https://phip1611.de | @phip1611
 *
 *
 * A list where each List Element knows what's the
 * next and whats the previous Element.
 */
public class BidirectionalList<T> extends LinearList<T> {

    /**
     * Reference to the very first Element of the list/the beginning.
     * Overrides the Type from the upper class LinearList.
     */
    protected BidirectionalListElement<T> listBegin;

    /**
     * Reference to the very last Element of the list.
     */
    protected BidirectionalListElement<T> listEnd;

    public BidirectionalList() {
        super();
    }

    /**
     * Determine if a specific value is already in the List (in an ListElement)!
     *
     * @param value
     * @return
     */
    @Override
    public Boolean isInList(T value) {
        // Vorwärts iterieren
        BidirectionalListElement currentListElement = this.listBegin;
        while (currentListElement.hasNext()) {
            if (currentListElement.getValue().equals(value)) {
               return true;
            }
            currentListElement = currentListElement.getNext();
        }
        return false;
    }

    /**
     * Appends data to the List.
     *
     * @param value
     */
    @Override
    public void append(T value) throws ListMaxSizeExceededException {
        if (this.listBegin == null) {
            // die Liste ist leer
            this.listBegin = new BidirectionalListElement<>(value);
            this.listEnd = this.listBegin;
        }
        else if (this.elementCount == 1) {
            this.listBegin.setNext(new BidirectionalListElement<>(value));
            this.listEnd = this.listBegin.getNext();
            this.listEnd.setPrevious(this.listBegin);

        } else {
            BidirectionalListElement tmp = this.listEnd;
            this.listEnd = new BidirectionalListElement(value);
            this.listEnd.setPrevious(tmp);
            tmp.setNext(this.listEnd);
        }
        this.elementCount++;
    }

    /**
     * Inserts data to the List.
     *
     * @param index
     * @param value
     */
    @Override
    public Boolean insert(Integer index, T value) throws ListMaxSizeExceededException {
        if (index < LIST_BEGIN || index > this.elementCount) {
            String exception;
            exception =  String.format("Index \"%d\" liegt außerhalb der Grenzen [%d,%d]\n",
                    index, LIST_BEGIN, this.elementCount);
            System.err.println(exception);
            throw new ListMaxSizeExceededException(exception);
        }
        else if (index == LIST_BEGIN) {
            BidirectionalListElement insertElement = new BidirectionalListElement(value);
            insertElement.setNext(this.listBegin);
            this.listBegin.setPrevious(insertElement);
            this.listBegin = insertElement;
        }
        else {
            int iterateCounter;
            BidirectionalListElement currentListElement;
            // Vorwärts iterieren
            if ((elementCount-index+1) > elementCount/2) {
                iterateCounter = List.LIST_BEGIN;
                currentListElement = this.listBegin;
                while (currentListElement.hasNext()) {
                    if (iterateCounter == index) {
                        BidirectionalListElement beforeElement, insertElement;
                        insertElement = new BidirectionalListElement(value);
                        beforeElement = currentListElement.getPrevious();
                        beforeElement.setNext(insertElement);
                        insertElement.setNext(currentListElement);
                        currentListElement.setPrevious(insertElement);
                        insertElement.setPrevious(beforeElement);
                        break;
                    }
                    currentListElement = currentListElement.getNext();
                    iterateCounter++;
                }
            }
            // Rückwärts iterieren
            else {
                iterateCounter = this.elementCount;
                currentListElement = this.listEnd;
                while (currentListElement.hasPrevious()) {
                    if (iterateCounter == index) {
                        BidirectionalListElement beforeElement, insertElement;
                        insertElement = new BidirectionalListElement(value);
                        beforeElement = currentListElement.getPrevious();
                        beforeElement.setNext(insertElement);
                        insertElement.setNext(currentListElement);
                        currentListElement.setPrevious(insertElement);
                        insertElement.setPrevious(beforeElement);
                        break;
                    }
                    currentListElement = currentListElement.getPrevious();
                    iterateCounter--;
                }
            }
        }
        this.elementCount++;
        return true;
    }

    /**
     * Returns the last Element and deletes it.
     * For example if you wan't ro realize a stack
     * this shit is cool as fuck!
     *
     * @return
     */
    @Override
    public T pop() {
        T returnThis;

        if (elementCount == 1) {
            returnThis = listBegin.getValue();
            listBegin = null;
            listEnd = null;
            elementCount = 0;
        }
        else {
            returnThis = listEnd.getValue();
            listEnd = listEnd.getPrevious();
            listEnd.setNext(null);
            elementCount--;
        }
        return returnThis;
    }

    /**
     * Returns content/value of a List at index.
     *
     * @param index
     * @return
     */
    @Override
    public T getValue(Integer index) {
        return null;
    }

    /**
     * Returns the index of the first
     * occurrence of the value in the List.
     *
     * @param value
     * @return
     */
    @Override
    public Integer getIndex(T value) {
        return null;
    }

    /**
     * Returns an Array of index of all
     * occurrences of the value in the List.
     *
     * @param value
     * @return
     */
    @Override
    public Integer[] getIndexes(T value) {
        return new Integer[0];
    }

    /**
     * Delets the List-Element at index.
     * Returns false if no element was deleted.
     *
     * @param index
     */
    @Override
    public Boolean deleteAt(Integer index) {
        return null;
    }

    /**
     * Deletes only the first occurrence of the value in the list.
     * Returns false if no element was deleted.
     *
     * @param value
     */
    @Override
    public Boolean delete(T value) {
        return null;
    }

    /**
     * Deletes all occurrences of the value in the list.
     * Returns false if no element was deleted.
     *
     * @param value
     * @return
     */
    @Override
    public Boolean deleteAll(T value) {
        return null;
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
     * Prints the List to the console. Has to be overwritten
     * in this context because otherwise a upper class
     * thinks the list begin is null because the type there is
     * linearListElement instead of BidirectionalListElement..
     */
    @Override
    public void printList() {
        BidirectionalListElement<T> current = this.listBegin;
        int i = List.LIST_BEGIN;
        if (current == null && elementCount == 0) {
            System.out.println("The List is empty!");
        }
        else if (current == null && elementCount != 0) {
            System.err.printf("printList() DEBUG: Reference to first Element is null but elementCount == %d != 0. Maybe a bug in the List-Class?\n", this.elementCount);
        }
        else if (current != null && elementCount == 0) {
            System.err.printf("printList() DEBUG: Reference to first Element is NOT null but elementCount == 0. Maybe a bug in the List-Class?\n", this.elementCount);
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
     * Prints the List BACKWARDS to the console.
     * @see this.printList()
     */
    public void printListBackwards() {
        BidirectionalListElement<T> current = this.listEnd;
        int i = this.elementCount;
        if (current == null && elementCount == 0) {
            System.out.println("The List is empty!");
        }
        else if (current == null && elementCount != 0) {
            System.err.printf("printList() DEBUG: Reference to first Element is null but elementCount == %d != 0. Maybe a bug in the List-Class?\n", this.elementCount);
        }
        else if (current != null && elementCount == 0) {
            System.err.printf("printList() DEBUG: Reference to first Element is NOT null but elementCount == 0. Maybe a bug in the List-Class?\n", this.elementCount);
        }
        else {
            while (current != null) {
                System.out.printf("Entrie %d: %s\n", i, current.toString());
                i--;
                current = current.getPrevious();
            }
        }
    }

}
