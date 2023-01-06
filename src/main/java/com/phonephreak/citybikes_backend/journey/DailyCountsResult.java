package com.phonephreak.citybikes_backend.journey;


public class DailyCountsResult {
    
    private String date;
    private long count;

    public DailyCountsResult(String date, long count){
        this.date = date;
        this.count = count;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public long getCount(){
        return this.count;
    }

    public void setCount(long count){
        this.count = count;
    }

}
