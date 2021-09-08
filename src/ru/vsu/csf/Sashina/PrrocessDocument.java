package ru.vsu.csf.Sashina;

public class PrrocessDocument {

    private DocInfo doc;
    private int start;
    private int finish;

    public PrrocessDocument (DocInfo doc, int start, int finish) {
        this.doc = doc;
        this.start = start;
        this.finish = finish;
    }

    public void setDoc (DocInfo d) {
        doc = d;
    }

    public void setStart (int s) {
        start = s;
    }

    public void setFinish (int f) {
        finish = f;
    }

    public DocInfo getDoc () {
        return doc;
    }

    public int getStart () {
        return start;
    }

    public int getFinish () {
        return finish;
    }
}
