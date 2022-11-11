package com.illinois.safetyratingsuiuc;

import java.util.Dictionary;

public class Location {
    private String lID;
    private String lName;
    private Dictionary lReviews;

    public Location(String id, String name) {
        lID = id;
        lName = name;
    }

    public String getID() {
        return lID;
    }

    public String getName() {
        return lName;
    }

    public Dictionary getReviews() {
        return lReviews;
    }

//    public void addReview(review) { lReviews[] = review; }
}
