package com.hackathon.test.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hackathon.main.configuration.RestParameters;
import com.hackathon.main.configuration.TestContext;
import com.hackathon.main.constants.ContextConstants;
import com.hackathon.main.domain.StateCovidData;
import com.hackathon.main.requestPojos.CovidMainAPI;
import com.hackathon.main.requestPojos.Statewise;
import com.hackathon.main.utilities.JsonUtils;
import com.hackathon.main.utilities.RestConnectionUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RestStepDefs extends BaseStepDefs {
    @Autowired
    RestConnectionUtils restConnectionUtils;

    @Autowired
    RestParameters restParameters;

    @Autowired
    JsonUtils jsonUtils;

    @Autowired
    TestContext testContext;

    @Autowired
    CovidMainAPI covidMainAPI;

    Logger logger = LoggerFactory.getLogger(RestConnectionUtils.class);

    @Given("user accesses the {string} api")
    public void userAccessTheAPI(String apiName) {
        restConnectionUtils.setBaseUrl(apiName);
    }

    @Then("user should be able to get response")
    public void userShouldBeAbleToGetResponse() {
        logger.info("The response is:{}", restParameters.getResponse());
    }

    @When("user performs a get request for {string} endpoint")
    public void userPerformsAGetRequestForEndpoint(String endpoint) {
        restConnectionUtils.getRequestPojo(endpoint);
    }

    @When("user performs a get request for {string}")
    public void userPerformsAGetRequestFor(String endpoint) {
        restConnectionUtils.getRequest(endpoint);
    }

    @When("user performs a post request")
    public void userPerformsAPostRequest() {
        restConnectionUtils.postRequest();
    }

    @Then("user validates that status code is {int}")
    public void userValidatesTheStatusCode(Integer statusCode) {
        Assert.assertTrue(restParameters.getStatusCode() == statusCode);
    }

    @And("validate data from the response based on {string}")
    public void validateDateFromResponseBasedOn(String criteria) {
        List<StateCovidData> covidDataList = testContext.getContext(ContextConstants.COVID_WEB_DATA);
        CovidMainAPI covidMainAPI = null;
        if (testContext.getContext(ContextConstants.COVID_API_DATA) instanceof CovidMainAPI) {
            covidMainAPI = testContext.getContext(ContextConstants.COVID_API_DATA);
        }
        switch (criteria.toUpperCase()) {
            case "STATE":
                List<Statewise> statewiseList = covidMainAPI.getStatewise();
                for (StateCovidData uiState : covidDataList) {
                    for (Statewise apiState : statewiseList) {
                        if (uiState.getStateOrUnionTeritory().equalsIgnoreCase(apiState.getState())) {
                            Assert.assertEquals("The total Recovered doesn't match", uiState.getTotalRecovered(), apiState.getRecovered());
                            Assert.assertEquals("The total active count doesn't match", uiState.getTotalDeceased(), apiState.getDeaths());
                            break;
                        }
                    }
                }
                break;
        }
    }

    @When("user updates json request nodes for {string} endpoint")
    public void userUpdatedJsonRequestNodeFor(String endPoint, DataTable dataTable) throws IOException {
        List<Map<String, String>> requestJsonValuesMap = dataTable.asMaps();
        restParameters.setEndPoint(endPoint);
        jsonUtils.setRequestJsonPath(endPoint);
        jsonUtils.updateJsonRequest(requestJsonValuesMap);
    }

    @And("fetch data from response for {string} for districts")
    public void fetchDataFromResponseFor(String fetchCategory) throws JsonProcessingException {
        String covidTopState = ContextConstants.COVID_TOP_STATE;
        testContext.setContext(ContextConstants.COVID_API_DATA, jsonUtils.fetchJsonResponseString(covidTopState, fetchCategory));
    }
}
