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
    private Integer departureStationId;
    private String departureStationName;
    private Integer returnStationId;
    private String returnStationName;
    private Float distance;    
    private Integer duration;    

    public Journey(){
        this.distance = 0f;
        this.duration = 0;
    }

    public Journey(Integer departureStationId,
                    String departureStationName,
                    Integer returnStationId,
                    String returnStationName,
                    Float distance,
                    Integer duration,
                    Date departureDate,
                    Date returnDate
                    ){
        this.departureStationId = departureStationId;
        this.departureStationName = departureStationName;
        this.returnStationId = returnStationId;
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

    public Integer getDepartureStationId(){
        return departureStationId;
    }

    public void setDepartureStationId(Integer departureStationId){
        this.departureStationId = departureStationId;
    }

    public String getDepartureStationName(){
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName){
        this.departureStationName = departureStationName;
    }

    public Integer getReturnStationId(){
        return returnStationId;
    }

    public void setReturnStationId(Integer returnStationId){
        this.returnStationId = returnStationId;
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
