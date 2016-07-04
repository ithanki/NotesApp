package com.example.ishita.notesapp;

import java.io.Serializable;

public class Pair implements Serializable{
    private String l;
    private String r;
    private Boolean checked_for_delete, long_press;

    public Pair() {
        super();
    }
    public Pair(String l, String r){
        super();
        this.l = l;
        this.r = r;
        this.checked_for_delete = false;
        this.long_press = false;

    }

    public String getL(){ return l; }
    public String getR(){ return r; }
    public Boolean getC(){ return checked_for_delete; }
    public Boolean getLP(){ return long_press; }
    public void flipL(){ this.long_press = !long_press; }
    public void flipC(){ this.checked_for_delete = !checked_for_delete; }
    public void setL(String l){ this.l = l; }
    public void setR(String r){ this.r = r; }
}
