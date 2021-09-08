package ru.vsu.csf.Sashina;

public class DocInfo {

    private int t,n,p,x;

    public DocInfo (int t, int n, int p, int x) {
        this.t = t;
        this.n = n;
        this.p = p;
        this.x = x;
    }

    public int getN() {
        return n;
    }

    public int getX() {
        return x;
    }

    public int getP() {
        return p;
    }

    public int getT() {
        return t;
    }
}
