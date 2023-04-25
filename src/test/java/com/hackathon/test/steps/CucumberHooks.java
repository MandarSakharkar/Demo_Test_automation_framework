package com.hackathon.test.steps;

import com.hackathon.main.utilities.ScreenshotService;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;


public class CucumberHooks {

    @Lazy
    @Autowired
    private ScreenshotService screenshotService;

    @Lazy
    @Autowired
    private ApplicationContext context;

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            scenario.attach(this.screenshotService.getScreenshot(), "image/png", scenario.getName());
        }
    }

    @After
    public void tearDown() {
        this.context.getBean(WebDriver.class).quit();
        if(this.context.containsBean(String.valueOf(AndroidDriver.class))) {
            this.context.getBean(AndroidDriver.class).quit();
        }
    }
}

