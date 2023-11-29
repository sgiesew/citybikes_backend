package com.phonephreak.citybikes_backend.journey;

import java.util.Date;
import java.util.Objects;

public class DailyCountsResult {
    
    private String x;
    private long y;

    public DailyCountsResult(String x, long y){
        this.x = x;
        this.y = y;
    }

    public DailyCountsResult(Date x, long y){
        this.x = x.toString().substring(0, 10);
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DailyCountsResult d = (DailyCountsResult) o;
        return x.equals(d.x) && y == d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

