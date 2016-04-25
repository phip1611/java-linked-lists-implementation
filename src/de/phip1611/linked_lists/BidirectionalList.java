package de.phip1611.linked_lists;

import java.util.ArrayList;
import java.util.Collection;

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
    public boolean append(T value) throws ListSizeExceededException {
        // Elemente dürfen nur einmalig hinzugefügt werden
        if (this.strictMode) {
            if (isInList(value)) {
                System.err.println(value + " ist bereits in der Liste!");
                throw new ElementAlreadyInListException(
                        "Element "+value.toString()+" ist bereits in der Liste. Strict Mode aktiv!");
                // return false;
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
        return true;
    }

    /**
     * Inserts data to the List.
     *
     * @param index
     * @param value
     */
    @Override
    public boolean insert(Integer index, T value) throws ListSizeExceededException {
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
    public boolean isInList(T value) {
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
    public boolean removeAt(Integer index) throws ListSizeExceededException {
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
     * Deletes all occurrences of the value in the list.
     * Returns false if no element was deleted.
     *
     * @param value
     * @return
     */
    @Override
    public boolean removeAll(T value) {
       return removeInternal(value, false);
    }

    private boolean removeInternal(T value, boolean justOneIteration) {
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
                if (justOneIteration) { // bei remove(Object o)
                    return deletedAny;
                }
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



    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return elementCount == 0 ? true : false;
    }

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     */
    @Override
    public boolean contains(Object o) {
        return this.isInList((T) o);
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     * <p>
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     * <p>
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this collection
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.elementCount];
        LinearListElement<T> currentListElement = this.listBegin;
        int counter = 0;
        while (currentListElement != null) {
            arr[counter] = currentListElement.getValue();
            currentListElement = currentListElement.getNext();
            counter++;
        }
        return arr;
    }

    /**
     * Returns an array containing all of the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this collection.
     * <p>
     * <p>If this collection fits in the specified array with room to spare
     * (i.e., the array has more elements than this collection), the element
     * in the array immediately following the end of the collection is set to
     * <tt>null</tt>.  (This is useful in determining the length of this
     * collection <i>only</i> if the caller knows that this collection does
     * not contain any <tt>null</tt> elements.)
     * <p>
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     * <p>
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     * <p>
     * <p>Suppose <tt>x</tt> is a collection known to contain only strings.
     * The following code can be used to dump the collection into a newly
     * allocated array of <tt>String</tt>:
     * <p>
     * <pre>
     *     String[] y = x.toArray(new String[0]);</pre>
     * <p>
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this collection are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collection
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < this.elementCount) {
            a = (T1[]) new Object[this.elementCount];
        }
        LinearListElement<T> currentListElement = this.listBegin;
        int counter = 0;
        while (currentListElement != null) {
            a[counter] = (T1) currentListElement.getValue();
            currentListElement = currentListElement.getNext();
            counter++;
        }
        return a;
    }

    /**
     * @alias for add()
     *
     * Ensures that this collection contains the specified element (optional
     * operation).  Returns <tt>true</tt> if this collection changed as a
     * result of the call.  (Returns <tt>false</tt> if this collection does
     * not permit duplicates and already contains the specified element.)<p>
     * <p>
     * Collections that support this operation may place limitations on what
     * elements may be added to this collection.  In particular, some
     * collections will refuse to add <tt>null</tt> elements, and others will
     * impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.<p>
     * <p>
     * If a collection refuses to add a particular element for any reason
     * other than that it already contains the element, it <i>must</i> throw
     * an exception (rather than returning <tt>false</tt>).  This preserves
     * the invariant that a collection always contains the specified element
     * after this call returns.
     *
     * @param t element whose presence in this collection is to be ensured
     * @return <tt>true</tt> if this collection changed as a result of the
     * call
     */
    @Override
    public boolean add(T t) {
        return this.append(t);
    }

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this collection contains one or more such elements.  Returns
     * <tt>true</tt> if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     */
    @Override
    public boolean remove(Object o) {
        return removeInternal((T) o, true);
    }

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return <tt>true</tt> if this collection contains all of the elements
     * in the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }
        for (Object a : c) {
            if (!this.isInList((T) a)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean addedAll = true;
        for (Object a : c) {
            addedAll = this.append((T) a);
        }
        return addedAll;
    }

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return <tt>true</tt> if this collection changed as a result of the
     * call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removedAll = true;
        for (Object a : c) {
            removedAll = this.remove((T) a);
        }
        return removedAll;
    }

    /**
     * WARNING: So far (2016-04-25) this is a experimental implementation
     * of java.util.Collection!
     *
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.size() > this.elementCount) {
            return false;
        }
        LinearListElement current = this.listBegin;
        while (current != null) {
            if (!c.contains(current.getValue())) {
                LinearListElement del = current;
                if (current.hasNext()) {
                    current = current.getNext();
                } else {
                    current = null;
                }
                this.remove(del);
            } else {
                current = current.getNext();
            }
        }
        return true;
    }




}
