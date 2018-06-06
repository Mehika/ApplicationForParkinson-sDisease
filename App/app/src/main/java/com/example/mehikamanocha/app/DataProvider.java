package com.example.mehikamanocha.app;

/**
 * Created by mehika.manocha on 12/03/2018.
 */

public class DataProvider {

    /* Setters and Getters of each of the columns in the database*/
    private String title;
    private String date;
    private String age;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String note;
    private long id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public DataProvider(String title, String date, long id, String note) {
        this.title = title;
        this.date = date;
        this.id = id;
        this.note = note;
        this.age = age;
    }
}