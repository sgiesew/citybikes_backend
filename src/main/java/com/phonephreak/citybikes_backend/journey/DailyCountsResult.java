package com.phonephreak.citybikes_backend.journey;


public class DailyCountsResult {
    
    private String date;
    private String count;

    public DailyCountsResult(String date, String count){
        this.date = date;
        this.count = count;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getCount(){
        return this.count;
    }

    public void setCount(String count){
        this.count = count;
    }

}
