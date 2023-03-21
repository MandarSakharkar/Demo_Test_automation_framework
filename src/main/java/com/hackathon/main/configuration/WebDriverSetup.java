package com.hackathon.main.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration

public class WebDriverSetup {


    @Value("${device.name}")
    private String deviceName;


    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver firefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "ie")
    public WebDriver edgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @Bean
    @ConditionalOnExpression("${mobile:true}")
    public WebDriver chromeMobileBrowser() {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", this.deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    @Bean
    @ConditionalOnMissingBean
    public WebDriver chromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }


}
