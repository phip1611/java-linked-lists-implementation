package de.phip1611.linked_lists;

/**
 * Monodirectional-Lists (not bidirectional) shall implement this.
 */
public abstract class LinearList<T> extends List<T> {
    public abstract void append(ListElement<T> le);
    public abstract ListElement<T> pop(ListElement<T> le);
    public abstract void delete(Integer index);
}
