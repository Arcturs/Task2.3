package ru.vsu.csf.Sashina;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JButton fileNameButton;
    private JTextField fileNameField;
    private JButton paramsButton;
    private JTextField paramsField;
    private JButton solutionButton;
    private JList<String> listDoc;
    private JTextField outputField;
    private JButton clearButton;
    private JLabel fileNameMistake;
    private JLabel paramsMistake;
    private JButton outputButton;
    private JLabel outputMistake;
    private JCheckBox checkQueue;
    private JLabel labelTimer;

    Comparator<DocInfo> priority = new Comparator<DocInfo>() {
        @Override
        public int compare(DocInfo a, DocInfo b) {
            if(a.getP()> b.getP())
                return 1;
            else if(a.getP()< b.getP())
                return -1;
            else
                return 0;
        }
    };
    Comparator<DocInfo> time = new Comparator<DocInfo>() {
        @Override
        public int compare(DocInfo a, DocInfo b) {
            if(a.getT()> b.getT())
                return 1;
            else if(a.getT()< b.getT())
                return -1;
            else
                return 0;
        }
    };
    Comparator<DocInfo> index = new Comparator<DocInfo>() {
        @Override
        public int compare(DocInfo a, DocInfo b) {
            if(a.getX()> b.getX())
                return 1;
            else if(a.getX()< b.getX())
                return -1;
            else
                return 0;
        }
    };

    Comparator<DocInfo> cc = priority.thenComparing(time).thenComparing(index);

    int t = 0;
    private Timer timer = new Timer(50000, e -> {
        t++;
        this.labelTimer.setText(Integer.toString(t));
    });

    DefaultListModel<String> model = new DefaultListModel<>();
    Print printer = new Print(new PriorityQueue<>(1, cc));
    MyPrint myPrinter = new MyPrint(new MyQueue<>());
    List<PrrocessDocument> list = new ArrayList<>();
    int print = 0;

    public FrameMain () {
        this.setTitle("Очередь");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        timer.start();

        fileNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = fileNameField.getText();
                    if (!name.contains(".txt")) {
                        name += ".txt";
                    }
                    BufferedReader bf = new BufferedReader(new FileReader(name));
                    String s = bf.readLine();
                    while (s != null) {
                        model.addElement(t + " " + s);
                        s = bf.readLine();
                    }
                    bf.close();
                    listDoc.setModel(model);
                    if (checkQueue.isSelected()) {
                        myPrinter.processFile(t, print, list, model);
                    } else {
                        printer.processFile(t, print, list, model);
                    }
                } catch (Exception exp) {
                    fileNameMistake.setText("Ошибка в чтении файла");
                    model.addElement(null);
                    listDoc.setModel(model);
                }
            }
        });

        paramsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = paramsField.getText();
                    model.addElement(t + " " + s);
                    listDoc.setModel(model);
                    if (checkQueue.isSelected()) {
                        myPrinter.processParams(t, print, list, s);
                    } else {
                        printer.processParams(t, print, list, s);
                    }
                } catch (Exception exp) {
                    paramsMistake.setText("Ошибка в чтении параметров");
                    model.addElement(null);
                    listDoc.setModel(model);
                }
            }
        });

        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    timer.stop();
                    if (checkQueue.isSelected()) {
                        list = myPrinter.solution(print, list);
                    } else {
                        list = printer.solution(print, list);
                    }
                    model.addElement("_______________________________________________");
                    for (PrrocessDocument doc : list) {
                        model.addElement("Старт печати: " + doc.getStart() + "  Конец печати: " + doc.getFinish()
                                + "  Параметры печати: " + doc.getDoc().getN() + " " + doc.getDoc().getP() + " "
                                + doc.getDoc().getX());
                        listDoc.setModel(model);
                    }
                } catch (Exception exp) {
                    model.addElement("Ошибка в обработке данных");
                    listDoc.setModel(model);
                }
            }
        });

        outputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = outputField.getText();
                    if (!name.contains(".txt")) {
                        name += ".txt";
                    }
                    File file1 = new File(name);
                    if (!file1.exists()){
                        file1.createNewFile();
                    }
                    PrintWriter pov = new PrintWriter(new FileWriter(file1, true));
                    ListModel<String> lm = listDoc.getModel();
                    if (list == null) {
                        pov.println("Ошибка в обработке данных");
                    } else {
                        for (int i = 0; i < lm.getSize(); i++) {
                            pov.println(lm.getElementAt(i));
                        }
                    }
                    pov.close();
                } catch (Exception er) {
                    outputMistake.setText("Ошибка в сохранении файла");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileNameField.setText("");
                paramsField.setText("");
                model.clear();
                outputField.setText("");
                fileNameMistake.setText("");
                paramsMistake.setText("");
                outputMistake.setText("");
                t = 0;
                printer = new Print(new PriorityQueue<>(1, cc));
                myPrinter = new MyPrint(new MyQueue<>());
                list = new ArrayList<>();
                print = 0;
                labelTimer.setText(t + " ");
                timer.start();
            }
        });
    }
}
