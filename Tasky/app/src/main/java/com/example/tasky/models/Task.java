package com.example.tasky.models;

/**
 * Created by Stjepan on 11.4.2016..
 */
public class Task {
    int id, imgRs;
    private String Title;
    private String Description;
    private String Time;

    public Task(int id, String title, String description, String time, int imgRs) {
        super();
        this.id = id;
        this.Title = title;
        this.Description = description;
        this.Time = time;
        this.imgRs = imgRs;
    }

    public Task(String title, String description, String time, int imgRs) {
        super();
        this.id = id;
        this.Title = title;
        this.Description = description;
        this.Time = time;
        this.imgRs = imgRs;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getTime() {
        return Time;
    }

    public int getImgRs() {
        return imgRs;
    }

    @Override
    public String toString() {
        String s;
        s = "Title: " + Title + " and time: " + Time;
        return s;
    }
}
