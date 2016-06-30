package com.example.ishita.notesapp;

public class Pair {
    private String l;
    private String r;
    private Boolean check;

    public Pair() {
        super();
    }
    public Pair(String l, String r){
        super();
        this.l = l;
        this.r = r;
        this.check = false;

    }

    public String getL(){ return l; }
    public String getR(){ return r; }
    public Boolean getC(){ return check; }
    public void flipC(){ this.check = !check; }
    public void setL(String l){ this.l = l; }
    public void setR(String r){ this.r = r; }
}
