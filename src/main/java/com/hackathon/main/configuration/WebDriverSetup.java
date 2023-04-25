package com.hackathon.main.configuration;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration

public class WebDriverSetup {


    @Value("${device.name}")
    private String deviceName;

    @Value("${appium.app}")
    private String appiumApp;

    @Value("${appium.udid}")
    private String appiumUdid;

    @Value("${appium.deviceName}")
    private String appiumDevice;

    @Value("${appium.automationName}")
    private String appiumAutomationName;

    @Value("${appium.url}")
    private String appiumUrl;

    @Value("${appium.platformName}")
    private String appiumPlatformName;

    @Value("${appium.appPackage}")
    private String appiumAppPackage;

    @Value("${appium.appActivity}")
    private String appiumAppActivity;

    @Value("${appium.uiautomator2ServerInstallTimeout}")
    private String uiautomator2ServerInstallTimeout;

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

    @SneakyThrows
    @Bean(name = "androidDriver")
    @Lazy
    @ConditionalOnClass(AndroidDriver.class)
    public AndroidDriver androidDriver() {
        DesiredCapabilities androidCaps = new DesiredCapabilities();

        androidCaps.setCapability("appium:deviceName", appiumDevice);
        androidCaps.setCapability("appium:automationName", appiumAutomationName);
        androidCaps.setCapability("appium:udid", appiumUdid);
        androidCaps.setCapability("appium:platformName", appiumPlatformName);
        androidCaps.setCapability("appium:app", System.getProperty("user.dir") + appiumApp);
        androidCaps.setCapability("appPackage", appiumAppPackage);
        androidCaps.setCapability("appActivity", appiumAppActivity);
        androidCaps.setCapability("uiautomator2ServerInstallTimeout", uiautomator2ServerInstallTimeout);

        return new AndroidDriver(new URL(appiumUrl), androidCaps);
    }

    @Bean
    @ConditionalOnMissingBean(WebDriver.class)
    public WebDriver chromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }
}
