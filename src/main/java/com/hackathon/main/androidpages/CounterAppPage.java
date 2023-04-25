package com.hackathon.main.androidpages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class CounterAppPage extends AndroidBasePage {

    Logger logger = LoggerFactory.getLogger(CounterAppPage.class);

    private String plusButtonId = "me.tsukanov.counter:id/incrementButton";
    private String counterId = "me.tsukanov.counter:id/counterLabel";

    public void clickPlusButton(){
        WebElement plusButtonElement = this.androidDriver.findElement(AppiumBy.id(plusButtonId));
        plusButtonElement.click();
    }

    public String getCounter(){
        WebElement counterElement = this.androidDriver.findElement(AppiumBy.id(counterId));
        return counterElement.getText();
    }
}
