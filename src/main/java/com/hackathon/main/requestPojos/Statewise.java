package com.hackathon.main.requestPojos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class Statewise {
    private String active;

    private String confirmed;

    private String deaths;

    private String deltaconfirmed;

    private String deltadeaths;

    private String deltarecovered;

    private String lastupdatedtime;

    private String migratedother;

    private String recovered;

    private String state;

    private String statecode;

    private String statenotes;

    public void setActive(String active){
        this.active = active;
    }
    public String getActive(){
        return this.active;
    }
    public void setConfirmed(String confirmed){
        this.confirmed = confirmed;
    }
    public String getConfirmed(){
        return this.confirmed;
    }
    public void setDeaths(String deaths){
        this.deaths = deaths;
    }
    public String getDeaths(){
        return this.deaths;
    }
    public void setDeltaconfirmed(String deltaconfirmed){
        this.deltaconfirmed = deltaconfirmed;
    }
    public String getDeltaconfirmed(){
        return this.deltaconfirmed;
    }
    public void setDeltadeaths(String deltadeaths){
        this.deltadeaths = deltadeaths;
    }
    public String getDeltadeaths(){
        return this.deltadeaths;
    }
    public void setDeltarecovered(String deltarecovered){
        this.deltarecovered = deltarecovered;
    }
    public String getDeltarecovered(){
        return this.deltarecovered;
    }
    public void setLastupdatedtime(String lastupdatedtime){
        this.lastupdatedtime = lastupdatedtime;
    }
    public String getLastupdatedtime(){
        return this.lastupdatedtime;
    }
    public void setMigratedother(String migratedother){
        this.migratedother = migratedother;
    }
    public String getMigratedother(){
        return this.migratedother;
    }
    public void setRecovered(String recovered){
        this.recovered = recovered;
    }
    public String getRecovered(){
        return this.recovered;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
    public void setStatecode(String statecode){
        this.statecode = statecode;
    }
    public String getStatecode(){
        return this.statecode;
    }
    public void setStatenotes(String statenotes){
        this.statenotes = statenotes;
    }
    public String getStatenotes(){
        return this.statenotes;
    }
}
