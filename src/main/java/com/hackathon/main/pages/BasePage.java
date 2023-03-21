package com.hackathon.main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public abstract class BasePage extends AbstractBasePage{

    @Lazy
    @Autowired
    protected WebDriver driver;
    @Lazy
    @Autowired
    protected WebDriverWait wait;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();
    }

    public abstract boolean isAt();
}
