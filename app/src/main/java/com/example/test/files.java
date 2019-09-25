package com.example.test;


public class files {
    private int id;
    private String fname;
    private String time;

    public files() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public  String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public files(int id, String fname, String time) {
        this.id = id;
        this.fname = fname;
        this.time = time;
    }

    public files(String fname, String time) {
        this.fname = fname;
        this.time = time;
    }
}