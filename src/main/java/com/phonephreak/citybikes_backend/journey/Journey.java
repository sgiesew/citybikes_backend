package com.phonephreak.citybikes_backend.journey;

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
    private String departure_station_code;
    private String departure_station_name;
    private String return_station_code;
    private String return_station_name;
    private Float distance;    
    private Integer duration;    

    public Journey(){
        this.distance = 0f;
        this.duration = 0;
    }

    public Journey(String departure_station_code,
                    String departure_station_name,
                    String return_station_code,
                    String return_station_name,
                    Float distance,
                    Integer duration
                    ){
        this.departure_station_code = departure_station_code;
        this.departure_station_name = departure_station_name;
        this.return_station_code = return_station_code;
        this.return_station_name = return_station_name;
        this.distance = distance;
        this.duration = duration;
    }

    public String getDeparture_station_code(){
        return departure_station_code;
    }

    public void setDeparture_station_code(String departure_station_code){
        this.departure_station_code = departure_station_code;
    }

    public String getDeparture_station_name(){
        return departure_station_name;
    }

    public void setDeparture_station_name(String departure_station_name){
        this.departure_station_name = departure_station_name;
    }

    public String getReturn_station_code(){
        return return_station_code;
    }

    public void setReturn_station_code(String return_station_code){
        this.return_station_code = return_station_code;
    }

    public String getReturn_station_name(){
        return return_station_name;
    }

    public void setReturn_station_name(String return_station_name){
        this.return_station_name = return_station_name;
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
