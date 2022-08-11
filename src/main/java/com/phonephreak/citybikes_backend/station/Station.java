package com.phonephreak.citybikes_backend.station;

import jakarta.persistence.*;

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
    private String station_code;
    private String name;
    private String address;
    private String city;
    private float pos_x;    
    private float pos_y;

    public Station(){

    }

    public Station(String station_code,
                    String name,
                    String address,
                    String city,
                    float pos_x,
                    float pos_y
                    ){
        this.station_code = station_code;
        this.name = name;
        this.address = address;
        this.city = city;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }

    public Integer getStation_id(){
        return station_id;
    }

    public void setStation_id(Integer station_id){
        this.station_id = station_id;
    }

    public String getStation_code(){
        return station_code;
    }

    public void setStation_code(String station_code){
        this.station_code = station_code;
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

    public void setPos_x(float pos_x){
        this.pos_x = pos_x;
    }

    public float getPos_x(){
        return pos_x;
    }

    public void setPos_y(float pos_y){
        this.pos_y = pos_y;
    }

    public float getPos_y(){
        return pos_y;
    }

}
