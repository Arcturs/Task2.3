package ru.vsu.csf.Sashina;

import javax.swing.*;
import java.util.List;

public class MyPrint {

    private MyQueue<DocInfo> queue;

    public MyPrint (MyQueue<DocInfo> queue) {
        this.queue = queue;
    }

    private MyQueue<DocInfo> addElements (DefaultListModel<String> model) {
        try {
            String [] str = new String[model.getSize()];
            for (int i = 0; i < model.getSize(); i++) {
                str[i] = model.getElementAt(i);
            }
            for (String value : str) {
                String [] str1 = value.split(" ");
                queue.add(new DocInfo(Integer.parseInt(str1[0]), Integer.parseInt(str1[1]), Integer.parseInt(str1[2]), Integer.parseInt(str1[3])));
            }
            return queue;
        } catch (Exception exp) {
            return null;
        }
    }

    private MyQueue<DocInfo> addElement (int t, String s) {
        try {
            String [] str1 = s.split(" ");
            queue.add(new DocInfo(t, Integer.parseInt(str1[0]), Integer.parseInt(str1[1]), Integer.parseInt(str1[2])));
            return queue;
        } catch (Exception exp) {
            return null;
        }
    }

    public List<PrrocessDocument> solution (int time, List<PrrocessDocument> list) {
        try {
            int start = time, end = time;
            while(queue.length() != 0) {
                DocInfo doc = queue.remove();
                end += doc.getN();
                list.add(new PrrocessDocument(doc, start, end));
                start = end;
                end = start;
            }
            return list;
        } catch (Exception err) {
            return null;
        }
    }

    public void processParams (int t, int print, List<PrrocessDocument> list, String s) throws Exception {
        if (t == print && print != 0) {
            DocInfo a;
            a = queue.remove();
            print += a.getN();
            list.add(new PrrocessDocument(a, t, print));
        }
        queue = addElement(t, s);
    }

    public void processFile (int t, int print, List<PrrocessDocument> list, DefaultListModel<String> model) throws Exception {
        if (t == print && print != 0) {
            DocInfo a;
            a = queue.remove();
            print += a.getN();
            list.add(new PrrocessDocument(a, t, print));
        }
        queue = addElements(model);
    }
}
