package com.example.ishita.notesapp;

public class Pair {
    private String l;
    private String r;
    private Boolean check, longP;

    public Pair() {
        super();
    }
    public Pair(String l, String r){
        super();
        this.l = l;
        this.r = r;
        this.check = false;
        this.longP = false;

    }

    public String getL(){ return l; }
    public String getR(){ return r; }
    public Boolean getC(){ return check; }
    public Boolean getLP(){ return longP; }
    public void flipL(){ this.longP = !longP; }
    public void flipC(){ this.check = !check; }
    public void setL(String l){ this.l = l; }
    public void setR(String r){ this.r = r; }
}
