package com.phonephreak.citybikes_backend.journey;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "journeys")
public class Journey {
    @Id
    @SequenceGenerator(
        name = "journeys_journey_id_seq",
        sequenceName = "journeys_journey_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "journeys_journey_id_seq"
    )
    private Integer journey_id;
    private Date departureDate;
    private Date returnDate;
    private String departureStationCode;
    private String departureStationName;
    private String returnStationCode;
    private String returnStationName;
    private Float distance;    
    private Integer duration;    

    public Journey(){
        this.distance = 0f;
        this.duration = 0;
    }

    public Journey(String departureStationCode,
                    String departureStationName,
                    String returnStationCode,
                    String returnStationName,
                    Float distance,
                    Integer duration,
                    Date departureDate,
                    Date returnDate
                    ){
        this.departureStationCode = departureStationCode;
        this.departureStationName = departureStationName;
        this.returnStationCode = returnStationCode;
        this.returnStationName = returnStationName;
        this.distance = distance;
        this.duration = duration;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public Date getDepartureDate(){
        return departureDate;
    }

    public void setDepartureDate(Date departureDate){
        this.departureDate = departureDate;
    }

    public Date getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(Date returnDate){
        this.returnDate = returnDate;
    }

    public String getDepartureStationCode(){
        return departureStationCode;
    }

    public void setDepartureStationCode(String departureStationCode){
        this.departureStationCode = departureStationCode;
    }

    public String getDepartureStationName(){
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName){
        this.departureStationName = departureStationName;
    }

    public String getReturnStationCode(){
        return returnStationCode;
    }

    public void setReturnStationCode(String returnStationCode){
        this.returnStationCode = returnStationCode;
    }

    public String getReturnStationName(){
        return returnStationName;
    }

    public void setReturnStationName(String returnStationName){
        this.returnStationName = returnStationName;
    }

    public void setDistance(Float distance){
        this.distance = distance;
    }

    public Float getDistance(){
        return distance;
    }

    public void setDuration(Integer duration){
        this.duration = duration;
    }

    public Integer getDuration(){
        return duration;
    }

}
