package com.hackathon.main.utilities;

import com.hackathon.main.configuration.RestParameters;
import com.hackathon.main.configuration.TestContext;
import com.hackathon.main.constants.ContextConstants;
import com.hackathon.main.requestPojos.CovidMainAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class RestConnectionUtils {

    Logger logger = LoggerFactory.getLogger(RestConnectionUtils.class);

    @Autowired
    RestParameters restParameters;

    @Autowired
    Environment environment;

    @Autowired
    TestContext testContext;

    @Autowired
    JsonUtils jsonUtils;

    private String baseUrl = "base.url.";
    private HttpHeaders httpHeaders;
    private RestTemplate restTemplate;

    public void getRequest(String endPoint) {
        httpHeaders = new HttpHeaders();
        restTemplate = new RestTemplate();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl + endPoint, HttpMethod.GET, httpEntity, String.class);
        logger.info("the url:{}", baseUrl + endPoint);
        HttpStatus statusCode = responseEntity.getStatusCode();
        restParameters.setStatusCode(statusCode.value());
        logger.info("The status code is: {}", statusCode);
        String response = responseEntity.getBody();
        restParameters.setResponse(response);
        logger.info("The response body: {}", response);
        httpHeaders = responseEntity.getHeaders();
        logger.info("The response headers: {}", httpHeaders);
        testContext.setContext("RestParams",restParameters);
        logger.info("The test context:{}", (Object) testContext.getContext("RestParams"));

    }

    public void getRequestPojo(String endPoint) {
        httpHeaders = new HttpHeaders();
        restTemplate = new RestTemplate();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<CovidMainAPI> responseEntity = restTemplate.exchange(baseUrl + endPoint, HttpMethod.GET, httpEntity, CovidMainAPI.class);
        CovidMainAPI covidMainAPI =responseEntity.getBody();
        testContext.setContext(ContextConstants.COVID_API_DATA, covidMainAPI);
        logger.info("the url:{}", baseUrl + endPoint);
        HttpStatus statusCode = responseEntity.getStatusCode();
        restParameters.setStatusCode(statusCode.value());
        logger.info("The status code is: {}", statusCode);
        httpHeaders = responseEntity.getHeaders();
        logger.info("The response headers: {}", httpHeaders);
        testContext.setContext("RestParams",restParameters);
    }

    public void postRequest() {
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(restParameters.getRequest(), httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl + restParameters.getEndPoint(), request, String.class);
        restParameters.setResponse(responseEntity.getBody());
        HttpStatus status = responseEntity.getStatusCode();
        restParameters.setStatusCode(status.value());

        logger.info("The response body: {}", responseEntity.getBody());
    }

    public void setBaseUrl(String apiName) {
        baseUrl = environment.getProperty(baseUrl + apiName);
    }
}
