package com.hackathon.main.requestPojos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class Cases_time_series {
    private String dailyconfirmed;

    private String dailydeceased;

    private String dailyrecovered;

    private String date;

    private  String dateymd;

    private String totalconfirmed;

    private String totaldeceased;

    private String totalrecovered;

    public void setDailyconfirmed(String dailyconfirmed){
        this.dailyconfirmed = dailyconfirmed;
    }
    public String getDailyconfirmed(){
        return this.dailyconfirmed;
    }
    public void setDailydeceased(String dailydeceased){
        this.dailydeceased = dailydeceased;
    }
    public String getDailydeceased(){
        return this.dailydeceased;
    }
    public void setDailyrecovered(String dailyrecovered){
        this.dailyrecovered = dailyrecovered;
    }
    public String getDailyrecovered(){
        return this.dailyrecovered;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setTotalconfirmed(String totalconfirmed){
        this.totalconfirmed = totalconfirmed;
    }
    public String getTotalconfirmed(){
        return this.totalconfirmed;
    }
    public void setTotaldeceased(String totaldeceased){
        this.totaldeceased = totaldeceased;
    }
    public String getTotaldeceased(){
        return this.totaldeceased;
    }
    public void setTotalrecovered(String totalrecovered){
        this.totalrecovered = totalrecovered;
    }
    public String getTotalrecovered(){
        return this.totalrecovered;
    }
}
