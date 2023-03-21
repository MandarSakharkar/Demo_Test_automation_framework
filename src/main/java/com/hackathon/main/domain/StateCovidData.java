package com.hackathon.main.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StateCovidData extends Covid19Data{
    private String totalConfirmed;
    private String totalActive;
    private String totalRecovered;
    private String totalDeceased;
    private String totalTested;
    private String stateOrUnionTeritory;

    public String getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public String getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(String totalActive) {
        this.totalActive = totalActive;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(String totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public String getTotalDeceased() {
        return totalDeceased;
    }

    public void setTotalDeceased(String totalDeceased) {
        this.totalDeceased = totalDeceased;
    }

    public String getTotalTested() {
        return totalTested;
    }

    public void setTotalTested(String totalTested) {
        this.totalTested = totalTested;
    }

    public String getStateOrUnionTeritory() {
        return stateOrUnionTeritory;
    }

    public void setStateOrUnionTeritory(String stateOrUnionTeritory) {
        this.stateOrUnionTeritory = stateOrUnionTeritory;
    }

    @Override
    public String toString() {
        return "StateCovidData{" + stateOrUnionTeritory + '\'' +
                "data :{"+super.toString()+"}"+
                '}';
    }
}
