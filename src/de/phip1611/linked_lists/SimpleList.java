package de.phip1611.linked_lists;

import java.util.Collection;

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
    public boolean append(T value) throws ListSizeExceededException {
        // Elemente dürfen nur einmalig hinzugefügt werden
        if (this.strictMode) {
            if (isInList(value)) {
                System.err.println(value + "ist bereits in der Liste!");
                throw new ElementAlreadyInListException(
                        "Element "+value.toString()+"ist bereits in der Liste. Strict Mode aktiv!");
                //return false; unreachable
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
        return true;
    }

    /**
     * Inserts data to the List. Index starts at 1.
     *
     * @param index
     * @param value
     */
    @Override
    public boolean insert(Integer index, T value) throws ListSizeExceededException {
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
        return null;
    }

    /**
     * Returns an Array of index of all
     * occurrences of the value in the List.
     * @param value
     * @return
     */
    @Override
    public Integer[] getIndexes(T value) {
        throw new UnsupportedOperationException("to be implemented");
    }

    /**
     * Delets the List-Element at index.
     *
     * @param index
     */
    @Override
    public boolean removeAt(Integer index) throws ListSizeExceededException {
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
     * Deletes all occurrences of the value in the list.
     *
     * @param value
     * @return
     */
    @Override
    public boolean removeAll(T value) {
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

    protected boolean remove (LinearListElement<T> lle) {
        System.out.println(lle.getValue());
        return this.remove(lle.getValue());
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
        T value = (T) o;
        // Vorwärts iterieren
        int count = List.LIST_BEGIN;
        LinearListElement currentListElement = this.listBegin, previousElement = null, nextElement;
        while (currentListElement != null) {
            if (currentListElement.getValue().equals(value)) {
                if (count == LIST_BEGIN) {
                    if (this.listBegin.hasNext()) {
                        this.listBegin = listBegin.getNext();
                    } else {
                        this.listBegin = null;
                    }
                } else {
                    nextElement = currentListElement.getNext();
                    if (previousElement != null) {
                        previousElement.setNext(nextElement);
                    }
                }

                elementCount--;
                return true;
            }
            previousElement = currentListElement;
            currentListElement = currentListElement.getNext();
            count++;
        }
        return false;
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
