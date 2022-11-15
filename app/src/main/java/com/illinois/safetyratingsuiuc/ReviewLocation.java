package com.illinois.safetyratingsuiuc;

import java.util.ArrayList;

public class ReviewLocation {
    private String name;
    private ArrayList<ArrayList<Review>> reviews;

    private ArrayList<String> timeBuckets = Constants.timeStrings;

    public ReviewLocation(String name, ArrayList<ArrayList<Review>> reviews) {
        this.name = name;
        this.reviews = reviews;
    }

    public ReviewLocation(String name) {
        // Create empty set of reviews
        this.name = name;
        this.reviews = new ArrayList<>();
        for (int i = 0; i < timeBuckets.size(); i++) {
            reviews.add(new ArrayList<>());
        }
    }

    public String getName() {
        return name;
    }

    /* Return review data as-is
     */
    public ArrayList<ArrayList<Review>> getReviewData() {
        return reviews;
    }

    public void setReviews(ArrayList<ArrayList<Review>> reviews) {
        this.reviews = reviews;
    }

    /* Get review list data for a certain time period
     *
     * @param timeInterval Index in time interval list to get data for or null to get all data
     * @return List of Review objects
     */
    public ArrayList<Review> getReviews(Integer timeInterval) {
        if(timeInterval == null) {
            ArrayList<Review> comb = new ArrayList<>();
            for (int i = 0; i < reviews.size(); i++) {
                comb.addAll(reviews.get(i));
            }
            return comb;
        } else {
            return reviews.get(timeInterval);
        }
    }

    public void addReview(int timeInterval, Review review) {
        reviews.get(timeInterval).add(0, review);
    }
}