package de.phip1611.linked_lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

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

        if (this.listBegin == null && index == LIST_BEGIN) {
            this.listBegin = le;
            this.elementCount++;
            return true;
        }
        else if (index == LIST_BEGIN) {
            le.setNext(this.listBegin);
            this.listBegin = le;
            this.elementCount++;
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
            this.elementCount++;
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
            while (listElement != null) {
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
    public T getValue(int index) {
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
        // Vorwärts iterieren
        int count = List.LIST_BEGIN;
        ArrayList<Integer> indexes = new ArrayList<>();
        LinearListElement currentListElement = this.listBegin;
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
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not implemented yet.");
        //return false;
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

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public T get(int index) {
        return this.getValue(index);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>set</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException("Operation not allowed. Rather delete and insert an element.");
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public void add(int index, T element) {
        this.insert(index, element);
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public T remove(int index) {
        if (this.indexInRange(index)) {
            T returnThis = this.getValue(index);
            this.removeAt(index);
            return returnThis;
        }
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int indexOf(Object o) {
        return this.getIndex((T)o);
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public int lastIndexOf(Object o) {
        Integer[] res = this.getIndexes((T) o);
        return res[res.length-1];
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Operation not implemented yet.");
        //return null;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Operation not implemented yet.");
        //return null;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for <tt>indexOf</tt> and
     * <tt>lastIndexOf</tt>, and all of the algorithms in the
     * <tt>Collections</tt> class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *                                   fromIndex &gt; toIndex</tt>)
     */
    @Override
    public java.util.List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Operation not implemented yet.");
        //return null;
    }
}
