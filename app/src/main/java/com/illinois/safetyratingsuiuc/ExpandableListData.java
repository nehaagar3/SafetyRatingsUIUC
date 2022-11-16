package com.illinois.safetyratingsuiuc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpandableListData {

    /**
     * Retrieves data for the SafeRides fragment.
     * @return data pertaining to the SafeRides FAQ
     */
    public static LinkedHashMap<String, List<String>> getSafeRidesData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> safeRidesInfo = new ArrayList<>();
        safeRidesInfo.add("The purpose of SafeRides Connect is to provide safe transportation to individuals " +
                "who are generally traveling alone and when no other means of safe transportation are available within" +
                " the designated SafeRides Connect boundaries. There is a maximum limit of three " +
                "persons per pick-up location. SafeRides Connect does not duplicate fixed-route " +
                "service already provided by MTD. SafeRides Connect does not provide emergency transportation" +
                " services to medical facilities. SafeRides Connect does not provide service to and from bars.\n" +
                "\n");


        List<String> howToSafeRides = new ArrayList<String>();
        howToSafeRides.add("Use the SafeRides Connect App. Download the SafeRides Connect App " +
                "from the Apple App Store or the Google Play Store and register yourself." +
                " Open the App, enter your origin, destination, number of people, and tap “Create Journey”. " +
                "The App will determine if a fixed-route trip is appropriate and will provide an itinerary. " +
                "If no fixed route is appropriate, a SafeRide Connect trip will be offered. " +
                "Tap “Book Trip” to accept the ride. Then monitor the App for updated trip information." +
                "\n" +
                "You can also call 217.384.8188. Provide the dispatcher with the following information: " +
                "your name (first and last), phone number, email address, pick-up location " +
                "(street address/landmark), and destination. If your trip is within the SafeRides " +
                "Connect boundaries, the dispatcher will enter the trip into the system and will give you the details of the trip");

        List<String> safeRidesWaitTime = new ArrayList<String>();
        howToSafeRides.add("Expect waiting times of 15 minutes during the week, and up to 30 minutes on the weekend when demand is higher. ");

        expandableListDetail.put("What is SafeRides?", safeRidesInfo);
        expandableListDetail.put("How do I use SafeRides?", howToSafeRides);
        expandableListDetail.put("What is the expected wait time for my SafeRide?", safeRidesWaitTime);

        return expandableListDetail;
    }

    /**
     * Retrieves data for the SafeWalks fragment.
     * @return data pertaining to the SafeWalks FAQ
     */
    public static LinkedHashMap<String, List<String>> getSafeWalksData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> safeWalksInfo = new ArrayList<>();
        safeWalksInfo.add("SafeWalks is a free nightly service provided to University of Illinois Urbana-Champaign " +
                "students, faculty, and staff. With just a phone call, trained Student Patrol officers " +
                "will come meet you and walk with you to your destination.\n" +
                "\n");


        List<String> howToSafeWalks = new ArrayList<String>();
        howToSafeWalks.add("To request a SafeWalks escort, call 217-333-1216. " +
                "You can also use an emergency phone to contact a dispatcher. " +
                "Please give at least 15 minutes notice.");

        expandableListDetail.put("What is SafeWalks?", safeWalksInfo);
        expandableListDetail.put("How do I use SafeWalks?", howToSafeWalks);

        return expandableListDetail;
    }

    /**
     * Retrieves data for the UIPD fragment.
     * @return data pertaining to the UIPD FAQ
     */
    public static LinkedHashMap<String, List<String>> getPoliceData() {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> policeInfo = new ArrayList<String>();
        policeInfo.add("The University of Illinois Police Department operates 24 hours a day, " +
                "365 days a year. It is fully-accredited like any other police department, and its officers " +
                "provide patrol services, emergency response and investigative resources.");

        List<String> howToPolice = new ArrayList<>();
        howToPolice.add("In case of an emergency, please call 911. Otherwise, the UIPD can be reached by" +
                "Calling (217) 333-1216.");


        List<String> additionalInfo = new ArrayList<>();
        additionalInfo.add("Patrol operations are the backbone of that effort. The most basic service " +
                "any police department can provide is a quick response when someone calls 911. " +
                "Our patrol officers do that and more. They are the first to respond when anyone needs an officer, " +
                "and their careful documenting and monitoring of criminal activity in our area provides the foundation investigators and crime prevention experts need to take public safety one step further. \n" +
                "\n" +
                "The Community Outreach and Support Team provides educational and community-oriented " +
                "program to empower our campus community members to look out for themselves and others. " +
                "Within COAST, the Response, Evaluation and Crisis Help (REACH) initiative is an innovative co-response model that pairs mental health professionals with police officers to respond to crisis emergencies.\n" +
                "\n" +
                "Detectives in our Investigations division provide services adapted for a college campus. " +
                "Detectives are specially trained to support and care for survivors of sexual assault," +
                " as well as investigate other crimes of a violent nature. When crime does happen, " +
                "it is our goal to identify and apprehend offenders as quickly as possible.\n");

        expandableListDetail.put("What is the UIPD?", policeInfo);
        expandableListDetail.put("How can I reach the UIPD?", howToPolice);
        expandableListDetail.put("What else should I know about the UIPD?", additionalInfo);

        return expandableListDetail;
    }

}
