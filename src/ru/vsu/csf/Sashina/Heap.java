package ru.vsu.csf.Sashina;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    private List<DocInfo> list = new ArrayList<>();

    public int getSize () {
        return list.size();
    }

    private boolean comparing (DocInfo a, DocInfo b) {
        if (a.getP() > b.getP() || (a.getP() == b.getP() && a.getT() > b.getT())
                || (a.getP() == b.getP() && a.getT() == b.getT() && a.getX() > b.getX())) {
            return true;
        } else {
            return false;
        }
    }

    public void add (DocInfo value) {
        list.add(value);
    }

    private void heapify (int i) {
        int left, right, max;
        while (true) {
            left = 2 * i + 1;
            right = 2 * i + 2;
            max = i;
            if (left < getSize() && !comparing(list.get(left), list.get(max))) {
                max = left;
            }
            if (right < getSize() && !comparing(list.get(right), list.get(max))) {
                max = right;
            }
            if (max == i) {
                break;
            }
            DocInfo temp = list.get(i);
            list.set(i, list.get(max));
            list.set(max, temp);
            i = max;
        }
    }

    public void buildHeap() {
        for (int i = getSize() / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    public DocInfo deleteFirst () {
        DocInfo result = list.remove(0);
        return result;
    }
}
