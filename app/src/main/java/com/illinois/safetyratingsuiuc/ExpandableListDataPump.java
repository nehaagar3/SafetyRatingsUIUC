package com.illinois.safetyratingsuiuc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap getSafeWalksAndSafeRidesData() {
        HashMap expandableListDetail = new HashMap();

        List safeRidesInfo = new ArrayList();
        safeRidesInfo.add("The purpose of SafeRides Connect is to provide safe transportation to individuals " +
                "who are generally traveling alone and when no other means of safe transportation are available within" +
                " the designated SafeRides Connect boundaries. There is a maximum limit of three " +
                "persons per pick-up location. SafeRides Connect does not duplicate fixed-route " +
                "service already provided by MTD. SafeRides Connect does not provide emergency transportation" +
                " services to medical facilities. SafeRides Connect does not provide service to and from bars.\n" +
                "\n");


        List safeWalksInfo = new ArrayList();
        safeWalksInfo.add("SafeWalks is a free nightly service provided to University of Illinois Urbana-Champaign " +
                "students, faculty, and staff. With just a phone call, trained Student Patrol officers " +
                "will come meet you and walk with you to your destination.\n" +
                "\n");

        List howToSafeRides = new ArrayList();
        howToSafeRides.add("woo");


        List howToSafeWalks = new ArrayList();
        howToSafeRides.add("To request a SafeWalks escort, call 217-333-1216. " +
                "You can also use an emergency phone to contact a dispatcher. " +
                "Please give at least 15 minutes notice.");


        expandableListDetail.put("What is SafeRides?", safeRidesInfo);
        expandableListDetail.put("What is SafeWalks?", safeWalksInfo);
        expandableListDetail.put("How do I use SafeRides?", howToSafeRides);
        expandableListDetail.put("How do I use SafeWalks?", howToSafeWalks);

        return expandableListDetail;
    }

    public static HashMap getPoliceData() {
        HashMap expandableListDetail = new HashMap();

        List policeInfo = new ArrayList();
        policeInfo.add("The University of Illinois Police Department operates 24 hours a day, " +
                "365 days a year. It is fully-accredited like any other police department, and its officers provide patrol services, emergency response and investigative resources.");

        List howToPolice = new ArrayList();
        howToPolice.add("In case of an emergency, please call 911. Otherwise, the UIPD can be reached by" +
                "Calling (217) 333-1216.");


        List additionalInfo = new ArrayList();
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

        List wow = new ArrayList();
        wow.add("whoa");


        expandableListDetail.put("What is the UIPD?", policeInfo);
        expandableListDetail.put("How can I reach the UIPD", howToPolice);
        expandableListDetail.put("What else should I know about the UIPD?", additionalInfo);
        expandableListDetail.put("yes?", wow);

        return expandableListDetail;
    }


}
