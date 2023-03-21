package com.hackathon.main.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidData {
    private String stateOrUnionTeritory;
    private String totalConfirmed;
    private String totalActive;
    private String totalRecovered;
    private String totalDeceased;
    private String totalTested;
}
