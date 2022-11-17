package com.illinois.safetyratingsuiuc;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class ReviewData {

    Hashtable<String, ReviewLocation> locationMap;
    Random random = new Random(5);

    public ReviewData() {
        // Using static hard-coded data.
        generateDummyData();
    }

    public ReviewLocation getReviewLocation(String location) {
        ReviewLocation rl = locationMap.get(location);
        if(rl == null) {
            locationMap.put(location, new ReviewLocation(location, createReviews(location)));
            // TODO This might be the source of some issues when adding new reviews for a location that doesn't exist yet
            return locationMap.get(location);
        } else {
            return rl;
        }
    }

    private void generateDummyData() {
        locationMap = new Hashtable<>();
    }

    private ArrayList<ArrayList<Review>> createReviews(String location) {
        ArrayList<ArrayList<Review>> locationReviews = new ArrayList();
        for(int i = 0; i < Constants.timeStrings.size(); i++) {
            int num_reviews = random.nextInt(6);
            ArrayList<Review> r = new ArrayList();

            for(int j = num_reviews; j > 0; j--) {
                float rating = ((float) random.nextInt(9) + 2) / 2; // between 1 and 5, step = 1/2
                r.add(new Review(location,
                        "October " + j + ", 2022",
                        Constants.timeStrings.get(i),
                        rating,
                        location + " test comment " + j));
            }

            locationReviews.add(r);
        }
        return locationReviews;
    }
}
