package com.phonephreak.citybikes_backend.station;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table (name = "stations")
public class Station {
    @Id
    @SequenceGenerator(
        name = "stations_station_id_seq",
        sequenceName = "stations_station_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "stations_station_id_seq"
    )
    private Integer station_id;
    @NotBlank
    private String stationCode;
    @NotBlank
    private String name;
    private String address;
    private String city;
    private float xPos;    
    private float yPos;

    public Station(){

    }

    public Station(String stationCode,
                    String name,
                    String address,
                    String city,
                    float xPos,
                    float yPos
                    ){
        this.stationCode = stationCode;
        this.name = name;
        this.address = address;
        this.city = city;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Integer getStation_id(){
        return station_id;
    }

    public void setStation_id(Integer station_id){
        this.station_id = station_id;
    }

    public String getStationCode(){
        return stationCode;
    }

    public void setStationCode(String stationCode){
        this.stationCode = stationCode;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setXPos(float xPos){
        this.xPos = xPos;
    }

    public float getXPos(){
        return xPos;
    }

    public void setYPos(float yPos){
        this.yPos = yPos;
    }

    public float getYPos(){
        return yPos;
    }

}
