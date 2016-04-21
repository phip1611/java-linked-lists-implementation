package de.phip1611.linked_lists;

import java.util.ArrayList;

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
     * Appends data to the List.
     *
     * @param value
     */
    @Override
    public void append(T value) throws ListSizeExceededException {
        // Elemente dürfen nur einmalig hinzugefügt werden
        if (this.strictMode) {
            if (isInList(value)) {
                System.err.println(value + " ist bereits in der Liste!");
                throw new ElementAlreadyInListException(
                        "Element "+value.toString()+" ist bereits in der Liste. Strict Mode aktiv!");
            }
        }

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
    public Boolean insert(Integer index, T value) throws ListSizeExceededException {
        // Elemente dürfen nur einmalig hinzugefügt werden
        if (this.strictMode) {
            if (isInList(value)) {
                System.err.println(value + "ist bereits in der Liste!");
                throw new ElementAlreadyInListException(
                        "Element "+value.toString()+"ist bereits in der Liste. Strict Mode aktiv!");
            }
        }


        indexInRange(index); // if not throws a ListSizeExceededException

        // Elemente dürfen nur einmalig hinzugefügt werden
        if (this.strictMode) {
            if (isInList(value)) {
                System.err.println(value + "ist bereits in der Liste!");
                throw new ElementAlreadyInListException(
                        "Element "+value.toString()+"ist bereits in der Liste. Strict Mode aktiv!");
            }
        }

        if (index == LIST_BEGIN) {
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
                        BidirectionalListElement previousElement, insertElement;
                        insertElement = new BidirectionalListElement(value);
                        previousElement = currentListElement.getPrevious();
                        previousElement.setNext(insertElement);
                        insertElement.setNext(currentListElement);
                        currentListElement.setPrevious(insertElement);
                        insertElement.setPrevious(previousElement);
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
                        BidirectionalListElement previousElement, insertElement;
                        insertElement = new BidirectionalListElement(value);
                        previousElement = currentListElement.getPrevious();
                        previousElement.setNext(insertElement);
                        insertElement.setNext(currentListElement);
                        currentListElement.setPrevious(insertElement);
                        insertElement.setPrevious(previousElement);
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
     * @throws ListSizeExceededException
     * @param index
     * @return
     */
    @Override
    public T getValue(Integer index) throws ListSizeExceededException {
        indexInRange(index); // if not it throws a Exception

        // Vorwärts iterieren
        int count;

        if (index == LIST_BEGIN) {
            return listBegin.getValue();
        }
        else if (index == elementCount) {
            return listEnd.getValue();
        }
        else if ((elementCount-index+1) > elementCount/2) {
            count = List.LIST_BEGIN;
            BidirectionalListElement currentListElement = this.listBegin;
            while (currentListElement != null) {
                if (count == index) {
                    return (T) currentListElement.getValue();
                }
                currentListElement = currentListElement.getNext();
                count++;
            }
        } else {
            count = elementCount;
            BidirectionalListElement currentListElement = this.listBegin;
            while (currentListElement != null) {
                if (count == index) {
                    return (T) currentListElement.getValue();
                }
                currentListElement = currentListElement.getPrevious();
                count--;
            }
        }

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
        // Vorwärts iterieren
        int count = List.LIST_BEGIN;
        BidirectionalListElement currentListElement = this.listBegin;
        while (currentListElement != null) {
            if (currentListElement.getValue().equals(value)) {
                return count;
            }
            currentListElement = currentListElement.getNext();
            count++;
        }
        return null;
    }

    /**
     * Determine if a specific value is already in the List (in a ListElement)!
     *
     * @param value
     * @return
     */
    public Boolean isInList(T value) {
        BidirectionalListElement<T> listElement = this.listBegin;
        while (listElement != null) {
            if (listElement.getValue().equals(value)) {
                return true;
            }
            listElement = listElement.getNext();
        }
        return false;
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
        // Vorwärts iterieren
        int count = List.LIST_BEGIN;
        ArrayList<Integer> indexes = new ArrayList<>();
        BidirectionalListElement currentListElement = this.listBegin;
        while (currentListElement != null) {
            if (currentListElement.getValue().equals(value)) {
                indexes.add(count);
            }
            currentListElement = currentListElement.getNext();
            count++;
        }
        return indexes.toArray(new Integer[indexes.size()]);
    }

    /**
     * Delets the List-Element at index.
     * Returns false if no element was deleted.
     * @throws ListSizeExceededException
     * @param index
     */
    @Override
    public Boolean deleteAt(Integer index) throws ListSizeExceededException {
        indexInRange(index); // if not throws a ListSizeExceededException
        if (index == LIST_BEGIN) {
            this.listBegin = listBegin.getNext();
            this.listBegin.setPrevious(null);
            this.elementCount--;
            return true;
        }
        else if (index == elementCount) { // Listenende
            this.listEnd = this.listEnd.getPrevious();
            this.listEnd.setNext(null);
            this.elementCount--;
            return true;
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
                        BidirectionalListElement previousElement, nextElement;
                        previousElement = currentListElement.getPrevious();
                        nextElement = currentListElement.getNext();
                        previousElement.setNext(nextElement);
                        nextElement.setPrevious(previousElement);
                        this.elementCount--;
                        return true;
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
                        BidirectionalListElement previousElement, nextElement;
                        previousElement = currentListElement.getPrevious();
                        nextElement = currentListElement.getNext();
                        previousElement.setNext(nextElement);
                        nextElement.setPrevious(previousElement);
                        this.elementCount--;
                        return true;
                    }
                    currentListElement = currentListElement.getPrevious();
                    iterateCounter--;
                }
            }
        }
        return false;
    }

    /**
     * Deletes only the first occurrence of the value in the list.
     * Returns false if no element was deleted.
     *
     * @param value
     */
    @Override
    public Boolean delete(T value) {
        // Vorwärts iterieren
        int count = List.LIST_BEGIN;
        BidirectionalListElement currentListElement = this.listBegin;
        while (currentListElement != null) {
            if (currentListElement.getValue().equals(value)) {
                BidirectionalListElement previousElement, nextElement;
                previousElement = currentListElement.getPrevious();
                nextElement = currentListElement.getNext();
                previousElement.setNext(nextElement);
                nextElement.setPrevious(previousElement);
                elementCount--;
                return true;
            }
            currentListElement = currentListElement.getNext();
            count++;
        }
        return false;
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
        // Vorwärts iterieren
        boolean deletedAny = false;
        BidirectionalListElement currentListElement = this.listBegin;
        while (currentListElement != null) {
            if (currentListElement.getValue().equals(value)) {
                if (elementCount == LIST_BEGIN) {
                    this.listBegin = null;
                    this.listEnd = null;
                }
                else if (currentListElement == listBegin) {
                    this.listBegin = this.listBegin.getNext();
                    this.listBegin.setPrevious(null);
                }
                else if (currentListElement == listEnd) {
                    this.listEnd = this.listEnd.getPrevious();
                    this.listEnd.setNext(null);
                }
                else {
                    BidirectionalListElement previousElement, nextElement;
                    previousElement = currentListElement.getPrevious();
                    nextElement = currentListElement.getNext();
                    previousElement.setNext(nextElement);
                    nextElement.setPrevious(previousElement);
                }
                deletedAny = true;
                elementCount--;
            }
            currentListElement = currentListElement.getNext();
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
        return "This BidirectionalList contains "+elementCount+" elements.";
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
     * Returns the last Element/value of the list without
     * deleting it (unlike pop()).
     */
    public T getLast() {
        if (listEnd != null) {
            return listEnd.getValue();
            //return getValue(elementCount);
        }
        return null;
    }

    /**
     * Clears a data structure / wipe's the fuck out of it.
     */
    @Override
    public void clear() {
        this.listBegin = null;
        this.listEnd = null;
        this.elementCount = 0;
        // Garbage Collection should kill all the listselements
        // cause they never will be used again
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
