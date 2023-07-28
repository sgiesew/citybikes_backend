package com.phonephreak.citybikes_backend.journey;


public class DailyCountsResult {
    
    private String x;
    private long y;

    public DailyCountsResult(String x, long y){
        this.x = x;
        this.y = y;
    }

    public String getX(){
        return this.x;
    }

    public void setX(String x){
        this.x = x;
    }

    public long getY(){
        return this.y;
    }

    public void setY(long y){
        this.y = y;
    }

}
