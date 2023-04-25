package com.hackathon.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com.hackathon.test.steps",
        stepNotifications = true,
        plugin = {"com.hackathon.main.Hooks.CustomReportListener", "pretty",
                "html:target/cucumber-html-reports/cucumber.html","json:target/cucumber.json", "rerun:target/failed_scenarios.txt"},
        monochrome = true
)
public class TestRunner {

}
