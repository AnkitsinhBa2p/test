package com.example.test;


public class files {
    private int id;
    private static String fname;
    private static String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getFname() {
        return fname;
    }

    public static void setFname(String fname) {
        files.fname = fname;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        files.time = time;
    }

    public files() {
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
