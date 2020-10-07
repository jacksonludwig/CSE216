package com.jackson.app;

import java.util.ArrayList;

public class Thing {
    private String name;
    private String id;
    private ArrayList<String> stuff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getTen() {
        return 10;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getStuff() {
        return stuff;
    }

    public void setStuff(ArrayList<String> stuff) {
        this.stuff = stuff;
    }

    public ArrayList<Long> values() {
        return null;
    }

}
