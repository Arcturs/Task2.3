package ru.vsu.csf.Sashina;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public class LinkedList<T> {

    private class Node<T> {
        public T value;
        public Node<T> next;

        public Node (T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int it = 0;
    private double EPS = 1E-9;

    public void addAtTheStart (T value) {
        head = new Node<>(value, head);
        if (it == 0) {
            tail = head;
        }
        it++;
    }

    public void addAtTheEnd (T value) {
        Node<T> newNode = new Node<>(value, null);
        if (it == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        it++;
    }

    public int size () {
        return it;
    }

    public boolean isEmpty () {
        boolean flag = false;
        if (it == 0) {
            flag = true;
        }
        return flag;
    }

    public T removeFirst() throws Exception {
        T value = getHead();
        head = head.next;
        it--;
        if (it == 0) {
            tail = null;
        }
        return value;
    }

    public T removeLast() throws Exception {
        T value = getTail();
        it--;
        if (it == 0) {
            head = tail = null;
        } else {
            tail = getNode(it - 1);
            tail.next = null;
        }
        return value;
    }

    public T getHead () throws Exception {
        if (isEmpty()) {
            throw new Exception("Лист пустой");
        } else {
            return head.value;
        }
    }

    public T getTail () throws Exception {
        if (isEmpty()) {
            throw new Exception("Лист пустой");
        } else {
            return tail.value;
        }
    }

    public T getValue (int index) throws Exception {
        if (index < 0 || index >= it) {
            throw new Exception("Неправильный индекс");
        } else {
            Node<T> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.value;
        }
    }

    private Node<T> getNode(int index) throws Exception {
        if (index < 0 || index >= it) {
            throw new Exception("Wrong index");
        }
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void insert(int index, T value) throws Exception {
        if (index < 0 || index > it) {
            throw new Exception("Wrong index");
        }
        if (index == 0) {
            addAtTheStart(value);
        } else {
            Node prev = getNode(index - 1);
            prev.next = new Node(value, prev.next);
            it++;
        }
    }

    public boolean contains (T value) {
        try {
            boolean flag = false;
            Node<T> current = head;
            for (int i = 0; i < it; i++) {
                if (Math.abs((double) value - (double) current.value) <= EPS) {
                    flag = true;
                    break;
                } else {
                    current = current.next;
                }
            }
            return flag;
        } catch (Exception exp) {
            return false;
        }
    }

    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            Node<T> current;

            public LinkedListIterator(Node<T> head) {
                current = head;
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T result = current.value;
                current = current.next;
                return result;
            }
        }

        return new LinkedListIterator(head);
    }

    public DocInfo [] toArray () {
        try {
            DocInfo [] arr = new DocInfo[it];
            Iterator<T> lt = iterator();
            int i = 0;
            while (lt.hasNext()) {
                arr[i] = (DocInfo) lt.next();
                i++;
            }
            return arr;
        } catch (Exception exp) {
            return null;
        }
    }
}
