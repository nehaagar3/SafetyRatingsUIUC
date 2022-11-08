package com.illinois.safetyratingsuiuc;

import java.util.Date;

public class Review {
    private String location;
    private String time;
    private String date;
    private float rating;
    private String comment;

    public Review(String location, String date, String time, float rating, String comment) {
        this.location = location;
        this.date = date;
        this.time = time;
        this.rating = rating;
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
