package ru.vsu.csf.Sashina;

import java.util.Iterator;

public interface InterfaceQueue<T> {
    void add(T value);
    DocInfo remove() throws Exception;
    T get() throws Exception;
    int length();
    void swap(T a, int i, T b, int j) throws Exception;
    T getNum (int index) throws Exception;
    Iterator<T> iter();
    DocInfo [] makeArray ();
}
