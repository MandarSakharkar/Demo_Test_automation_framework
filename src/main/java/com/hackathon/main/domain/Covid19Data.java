package com.hackathon.main.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Covid19Data {
    private String totalConfirmed;
    private String totalActive;
    private String totalRecovered;
    private String totalDeceased;
    private String totalTested;

    @Override
    public String toString() {
        return "Covid19Data{" +
                "totalConfirmed='" + totalConfirmed + '\'' +
                ", totalActive='" + totalActive + '\'' +
                ", totalRecovered='" + totalRecovered + '\'' +
                ", totalDeceased='" + totalDeceased + '\'' +
                ", totalTested='" + totalTested + '\'' +
                '}';
    }
}
