package com.hackathon.test.steps.androidappsteps;

import com.hackathon.main.androidpages.CounterAppPage;
import com.hackathon.test.steps.BaseStepDefs;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CounterAppStepDefs extends BaseStepDefs{

    @Autowired
    CounterAppPage counterAppPage;

    @Then("User verifies that counter value is {string}")
    public void userVerifiesTheCounterValue(String count) {
        Assert.assertEquals(count, counterAppPage.getCounter());
    }

    @When("User press the plus button")
    public void userPressThePlusButton() {
        counterAppPage.clickPlusButton();
    }
}
