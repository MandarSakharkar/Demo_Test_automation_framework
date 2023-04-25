package com.hackathon.main.androidpages;

import com.hackathon.main.pages.AbstractBasePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public abstract class AndroidBasePage {

    @Lazy
    @Autowired(required = false)
    @Qualifier("androidDriver")
    protected AndroidDriver androidDriver ;
}
