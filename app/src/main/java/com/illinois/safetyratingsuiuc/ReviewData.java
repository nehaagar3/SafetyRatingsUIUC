package com.illinois.safetyratingsuiuc;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;


// Static hard-coded data.
// TODO integrate with Location class
public class ReviewData {

    Random random = new Random(5);
    ArrayList<ArrayList<Review>> locationReviews;

    public ReviewData() {
        locationReviews = new ArrayList();
        for(int i = 0; i < Constants.timeStrings.size(); i++) {
            int num_reviews = random.nextInt(6);
            ArrayList<Review> r = new ArrayList();

            for(int j = num_reviews; j > 0; j--) {
                float rating = ((float) random.nextInt(9) + 2) / 2; // between 1 and 5, step = 1/2
                r.add(new Review("Main Quad",
                        "October " + j + ", 2022",
                        Constants.timeStrings.get(i),
                        rating,
                        "Test Comment " + j));
            }

            locationReviews.add(r);
        }
    }

    public ArrayList<Review> getReviews(int timeInterval, boolean all) {
        if(all) {
            ArrayList<Review> comb = new ArrayList<>();
            for (int i = 0; i < locationReviews.size(); i++) {
                comb.addAll(locationReviews.get(i));
            }
            return comb;
        } else {
            return locationReviews.get(timeInterval);
        }
    }

    public ArrayList<ArrayList<Review>> getLocationReviews() {
        return locationReviews;
    }

}
