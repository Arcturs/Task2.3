package ru.vsu.csf.Sashina;

import java.util.Iterator;

public class MyQueue<T> extends LinkedList<T> implements InterfaceQueue<T>{

    private Heap heap = new Heap();

    @Override
    public void add(T value) {
        heap.add((DocInfo) value);
        addAtTheEnd(value);
    }

    @Override
    public DocInfo remove() throws Exception {
        heap.buildHeap();
        return heap.deleteFirst();
    }

    @Override
    public T get() throws Exception {
        return getHead();
    }

    @Override
    public int length() {
        return heap.getSize();
    }

    @Override
    public void swap(T a, int i, T b, int j) throws Exception{
        insert(i, b);
        insert(j, a);
    }

    @Override
    public T getNum (int index) throws Exception {
        return getValue(index);
    }

    @Override
    public Iterator<T> iter() {
        return iterator();
    }

    @Override
    public DocInfo [] makeArray () {
        return toArray();
    }
}
