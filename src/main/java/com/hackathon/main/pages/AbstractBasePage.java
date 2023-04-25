package com.hackathon.main.pages;

import com.hackathon.main.constants.Action;
import com.hackathon.main.constants.ActionResult;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import com.google.common.base.Function;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Component
public class AbstractBasePage {

    private static final int SLEEP_TIME_MILLIS = 6500;
    private static final long TIMEOUT = 180;
    @Autowired
    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(AbstractBasePage.class);

    public void enterText(WebElement field, String fieldValue){
        waitForElementVisibility(field);
        field.sendKeys(fieldValue);
    }

    public void clickWhenClickable(WebElement element){
        waitForElementVisibility(element);
        element.click();
    }

    public void selectByVisibleText(WebElement element,String optionText){
        doSelectByVisibleText(element,optionText);
    }

    public void selectByIndex(WebElement element,int index){
        waitUntilSelectOptionsPopulated(element);
        select(element).selectByIndex(index);
    }

    public void selectByValue(WebElement element,String value){
        waitUntilSelectOptionsPopulated(element);
        select(element).selectByValue(value);
    }

    public void clickCheckBox(WebElement checkBoxOption,boolean flag){
        waitForElementVisibility(checkBoxOption);
        if(checkBoxOption.isSelected()!=flag){
            clickWhenClickable(checkBoxOption);
        }
    }

    public String getTextFromPage(WebElement element){
        waitForElementVisibility(element);
        return element.getText();
    }

    public WebElement element(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public List<WebElement> elements(String xpath){
        return driver.findElements(By.xpath(xpath));
    }

    public void switchToFrame(String strFrame){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        try{
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(strFrame));
        } catch (Exception exception){
            logger.info("Unable to switch to frame: {} due to :{} ",strFrame,exception.getMessage());
        }
    }

    public boolean acceptAlert(){
        Alert alert = driver.switchTo().alert();
        try{
            alert.accept();
            return true;
        } catch (NoAlertPresentException exception){
            logger.info("Expected alert not preset due to : {}",exception.getMessage());
            return false;
        }
    }

    public String doAlertAction(Action action, String... text){
        Alert alert = driver.switchTo().alert();
        try {
            switch (action) {
                case ACCEPT:
                    alert.accept();
                    break;
                case REJECT:
                    alert.dismiss();
                    break;
                case GET_TEXT:
                    String message = alert.getText();
                    alert.accept();
                    return message;
                case ENTER_TEXT:
                    alert.sendKeys(text[0]);
                    alert.accept();
                    break;
            }
            return ActionResult.SUCCESS;
        } catch (NoAlertPresentException exception){
            logger.info("Alert Action failed due to reason : {}",exception.getMessage());
            return ActionResult.FAILED;
        }
    }

    public boolean rejectAlert(){
        Alert alert = driver.switchTo().alert();
        try{
            alert.dismiss();
            return true;
        } catch (NoAlertPresentException exception){
            logger.info("Expected alert not preset due to : {}",exception.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public <T>T executeJavaScript(WebDriver driver, String javaScript, Object... args){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (T) executor.executeScript(javaScript,args);
    }

    public static <F>F fluentWait(Supplier<F> condition){
        return new FluentWait<>(new Object()).ignoring(WebDriverException.class)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(30))
                .until((Function<Object,F>)o->condition.get());
    }

    public boolean waitForElementVisibility(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(SLEEP_TIME_MILLIS));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            logger.info("Element is visible");
            executeJavaScript(driver,"arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');",webElement);
            return true;
        } catch (Exception exception){
            logger.info("Element not visible : {}",exception.getMessage());
            return false;
        }
    }

    private void waitUntilSelectOptionsPopulated(WebElement element) {
        waitForElementVisibility(element);
        new WebDriverWait(driver,Duration.ofSeconds(30))
                .until(
                        (ExpectedCondition<Boolean>) webDriver -> select(element).getOptions().size()>1
                );
    }

    public Select select(WebElement element){
        return new Select(element);
    }

    private void doSelectByVisibleText(WebElement element,String optionText) {
        AtomicReference<String> caseInsensitiveVisibleText = new AtomicReference<>("");
        waitUntilSelectOptionsPopulated(element);
        List<WebElement> selectOptions = select(element).getOptions();
        selectOptions.forEach(option ->{
            if(option.getText().toUpperCase().contains(optionText.toUpperCase())){
                caseInsensitiveVisibleText.set(option.getText());
            }
        });
        select(element).selectByVisibleText(caseInsensitiveVisibleText.get());
    }

    public String getCovidDataValueByXpath(String xpath){
        return getTextFromPage(element(xpath)).replace(",","");
    }

}
