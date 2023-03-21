package com.hackathon.main.requestPojos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
public class CovidMainAPI {


    private List<Cases_time_series> cases_time_series;

    private List<Statewise> statewise;

    private List<Tested> tested;

    public void setCases_time_series(List<Cases_time_series> cases_time_series) {
        this.cases_time_series = cases_time_series;
    }

    public List<Cases_time_series> getCases_time_series() {
        return this.cases_time_series;
    }

    public void setStatewise(List<Statewise> statewise) {
        this.statewise = statewise;
    }

    public List<Statewise> getStatewise() {
        return this.statewise;
    }

    public void setTested(List<Tested> tested) {
        this.tested = tested;
    }

    public List<Tested> getTested() {
        return this.tested;
    }
}

