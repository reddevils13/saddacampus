package com.example.win10.myapplication;

/**
 * Created by win10 on 3/7/2018.
 */

public class player
{
    String name;
    int id;

    public player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
