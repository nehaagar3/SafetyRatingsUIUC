package com.illinois.safetyratingsuiuc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListData {
    public static HashMap<String, List<String>> getSafeWalksAndSafeRidesData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> safeRidesInfo = new ArrayList<>();
        safeRidesInfo.add("The purpose of SafeRides Connect is to provide safe transportation to individuals " +
                "who are generally traveling alone and when no other means of safe transportation are available within" +
                " the designated SafeRides Connect boundaries. There is a maximum limit of three " +
                "persons per pick-up location. SafeRides Connect does not duplicate fixed-route " +
                "service already provided by MTD. SafeRides Connect does not provide emergency transportation" +
                " services to medical facilities. SafeRides Connect does not provide service to and from bars.\n" +
                "\n");


        List<String> safeWalksInfo = new ArrayList<>();
        safeWalksInfo.add("SafeWalks is a free nightly service provided to University of Illinois Urbana-Champaign " +
                "students, faculty, and staff. With just a phone call, trained Student Patrol officers " +
                "will come meet you and walk with you to your destination.\n" +
                "\n");

        List<String> howToSafeRides = new ArrayList<String>();
        howToSafeRides.add("Use the SafeRides Connect App. Download the SafeRides Connect App " +
                "from the Apple App Store or the Google Play Store and register yourself." +
                " Open the App, enter your origin, destination, number of people, and tap “Create Journey”. " +
                "The App will determine if a fixed-route trip is appropriate and will provide an itinerary. " +
                "If no fixed route is appropriate, a SafeRide Connect trip will be offered. " +
                "Tap “Book Trip” to accept the ride. Then monitor the App for updated trip information.");


        List<String> howToSafeWalks = new ArrayList<String>();
        howToSafeWalks.add("To request a SafeWalks escort, call 217-333-1216. " +
                "You can also use an emergency phone to contact a dispatcher. " +
                "Please give at least 15 minutes notice.");


        expandableListDetail.put("What is SafeRides?", safeRidesInfo);
        expandableListDetail.put("What is SafeWalks?", safeWalksInfo);
        expandableListDetail.put("How do I use SafeRides?", howToSafeRides);
        expandableListDetail.put("How do I use SafeWalks?", howToSafeWalks);

        return expandableListDetail;
    }

    public static HashMap<String, List<String>> getPoliceData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> policeInfo = new ArrayList<String>();
        policeInfo.add("The University of Illinois Police Department operates 24 hours a day, " +
                "365 days a year. It is fully-accredited like any other police department, and its officers provide patrol services, emergency response and investigative resources.");

        List<String> howToPolice = new ArrayList<>();
        howToPolice.add("In case of an emergency, please call 911. Otherwise, the UIPD can be reached by" +
                "Calling (217) 333-1216.");


        List<String> additionalInfo = new ArrayList<>();
        additionalInfo.add("Patrol operations are the backbone of that effort. The most basic service " +
                "any police department can provide is a quick response when someone calls 911. " +
                "Our patrol officers do that and more. They are the first to respond when anyone needs an officer, " +
                "and their careful documenting and monitoring of criminal activity in our area provides the foundation investigators and crime prevention experts need to take public safety one step further. \n" +
                "The Community Outreach and Support Team provides educational and community-oriented " +
                "program to empower our campus community members to look out for themselves and others. " +
                "Within COAST, the Response, Evaluation and Crisis Help (REACH) initiative is an innovative co-response model that pairs mental health professionals with police officers to respond to crisis emergencies.\n" +
                "Detectives in our Investigations division provide services adapted for a college campus. " +
                "Detectives are specially trained to support and care for survivors of sexual assault," +
                " as well as investigate other crimes of a violent nature. When crime does happen, " +
                "it is our goal to identify and apprehend offenders as quickly as possible.\n");


        expandableListDetail.put("What is the UIPD?", policeInfo);
        expandableListDetail.put("How can I reach the UIPD", howToPolice);
        expandableListDetail.put("What else should I know about the UIPD?", additionalInfo);

        return expandableListDetail;
    }


}