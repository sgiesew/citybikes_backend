package com.phonephreak.citybikes_backend.station;

import java.util.ArrayList;

import com.phonephreak.citybikes_backend.journey.DailyCountsResult;


public class StationDetail {
    
    private String name;
    private String address;
    private String city;
    private float xPos;    
    private float yPos;
    private long numDepartures;
    private long numReturns;
    private long averageDepartureDistance;
    private long averageReturnDistance;
    private ArrayList<String> returnedToFromStationRanked;
    private ArrayList<String> departedFromToStationRanked;
    private ArrayList<DailyCountsResult> dailyDeparturesFromStation;
    private ArrayList<DailyCountsResult> dailyReturnsToStation;

    public StationDetail(){

    }

    public StationDetail(String name,
                        String address,
                        String city,
                        float xPos,    
                        float yPos,
                        long numDepartures,
                        long numReturns,
                        long averageDepartureDistance,
                        long averageReturnDistance,
                        ArrayList<String> returnedToFromStationRanked,
                        ArrayList<String> departedFromToStationRanked,
                        ArrayList<DailyCountsResult> dailyDeparturesFromStation,
                        ArrayList<DailyCountsResult> dailyReturnsToStation
                        ){
        this.name = name;
        this.address = address;
        this.city = city;
        this.xPos = xPos;
        this.yPos = yPos;
        this.numDepartures = numDepartures;
        this.numReturns = numReturns;
        this.averageDepartureDistance = averageDepartureDistance;
        this.averageReturnDistance = averageReturnDistance;
        this.returnedToFromStationRanked = returnedToFromStationRanked;
        this.departedFromToStationRanked = departedFromToStationRanked;
        this.dailyDeparturesFromStation = dailyDeparturesFromStation;
        this.dailyReturnsToStation = dailyReturnsToStation;
                                                }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public long getNumDepartures() {
        return numDepartures;
    }

    public long getNumReturns() {
        return numReturns;
    }

    public long getAverageDepartureDistance() {
        return averageDepartureDistance;
    }

    public long getAverageReturnDistance() {
        return averageReturnDistance;
    }

    public ArrayList<String> getReturnedToFromStationRanked() {
        return returnedToFromStationRanked;
    }

    public ArrayList<String> getDepartedFromToStationRanked() {
        return departedFromToStationRanked;
    }

    public ArrayList<DailyCountsResult> getDailyDeparturesFromStation() {
        return dailyDeparturesFromStation;
    }

    public ArrayList<DailyCountsResult> getDailyReturnsToStation() {
        return dailyReturnsToStation;
    }

        
}
