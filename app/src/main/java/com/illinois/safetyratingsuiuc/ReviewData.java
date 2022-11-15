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
            // TODO This might be the source of some issues when adding new reviews for a location that doesn't exist yet
            return new ReviewLocation(location);
        } else {
            return rl;
        }
    }

    private void generateDummyData() {
        locationMap = new Hashtable<>();

        String main_quad = "Main Quad";
        locationMap.put(main_quad, new ReviewLocation(main_quad, createReviews(main_quad)));

        for (int i = 1; i < 6; i++) {
            // TODO make these real locations
            String loc = "Location " + String.valueOf(i);

            ReviewLocation location = new ReviewLocation(loc, createReviews(loc));
            locationMap.put(String.valueOf(i), location);
        }
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
                        "Test Comment " + j));
            }

            locationReviews.add(r);
        }
        return locationReviews;
    }
}