package com.hackathon.test.steps;

import com.hackathon.main.configuration.TestContext;
import com.hackathon.main.constants.ContextConstants;
import com.hackathon.main.pages.Covid19Page;
import com.hackathon.main.utilities.ScreenshotService;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CovidStepDefs extends BaseStepDefs implements En {

    @Autowired
    private Covid19Page page;

    @Autowired
    ScreenshotService screenshotService;

    @Autowired
    TestContext testContext;
    Logger logger = LoggerFactory.getLogger(CovidStepDefs.class);

    public CovidStepDefs() {
        Given("when user navigates to covid page", () -> {
            page.goTo();
            testContext.setContext(ContextConstants.COVID_WEB_DATA,page.getStateData());
            testContext.setContext(ContextConstants.COVID_WEB_DATA_DISTRICT,page.getDistrictData());
            screenshotService.getScreenshot();
        });
        And("validate the districts {string} on web", (String category) -> {
            Map<String, String> districtData = testContext.getContext(ContextConstants.COVID_WEB_DATA_DISTRICT);
            Map<String, String> districtDataApi = testContext.getContext(ContextConstants.COVID_API_DATA);
            Assert.assertTrue("The " + category + " for districts doesn't match!", districtData.equals(districtDataApi));
        });
    }
}