package com.hackathon.main.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
public class RestParameters {

    private String request;
    public String response;
    public int statusCode = 0;
    private String responseHeaders;
    private String requestJsonPath;
    private String endPoint;
}
