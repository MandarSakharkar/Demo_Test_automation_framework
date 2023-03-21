package com.hackathon.main.pages;


import com.hackathon.main.domain.StateCovidData;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class Covid19Page extends BasePage{

    @Value("${application.url}")
    private String applicationUrl;
    public void goTo() {
        this.driver.get(applicationUrl);
    }

    @FindBy(xpath = "//div[contains(text(),'Recovered')]")
    WebElement recovered;

    Logger logger = LoggerFactory.getLogger(Covid19Page.class);

    List<StateCovidData> covidDataStates = new ArrayList<>();


    public List<StateCovidData> getStateData(){
        clickWhenClickable(recovered);
        String rowPath = "//div[@class='row'][%s]";
        for (int itr=1; itr<=3;itr++){
            String baseXPath = String.format(rowPath,itr);
            String stateXPath = baseXPath+"//*[@class='state-name fadeInUp']";
            String confirmedXPath = baseXPath+"//*[@class='delta is-confirmed']/following-sibling::div[1]";
            String recoveredXPath = baseXPath+"//*[@class='delta is-recovered']/following-sibling::div[1]";
            String activeXPath = baseXPath+"//*[@class='total']";
            String deaceasedXPath = baseXPath+"//*[@class='delta is-deceased']/following-sibling::div[1]";
            String testedXPath = baseXPath+"//*[@class='delta is-tested']/following-sibling::div[1]";

            StateCovidData data = new StateCovidData();
            data.setTotalActive(getCovidDataValueByXpath(activeXPath));
            data.setTotalConfirmed(getCovidDataValueByXpath(confirmedXPath));
            data.setStateOrUnionTeritory(getCovidDataValueByXpath(stateXPath));
            data.setTotalDeceased(getCovidDataValueByXpath(deaceasedXPath));
            data.setTotalRecovered(getCovidDataValueByXpath(recoveredXPath));
            data.setTotalTested(getCovidDataValueByXpath(testedXPath));
            covidDataStates.add(data);
        }
        logger.info("covidDataStates : {} ", covidDataStates);
        return covidDataStates;
    }

    @Override
    public boolean isAt() {
        return false;
    }

    public Map<String,String> getDistrictData() throws InterruptedException {
        String state="Maharashtra";
        //  String stateXpath=String.format("//*[contains(text(),'Maharashtra') and contains(@class,’state-name')]",state);
        this.driver.findElement(By.xpath("//*[contains(text(),'Maharashtra') and contains(@class,'fadeInUp')]")).click();
        this.driver.findElement(By.xpath("//div[@class='state-page']")).click();
        Thread.sleep(5000);
        this.driver.findElement(By.xpath("//div[@class='clickable is-recovered']")).click();
        Thread.sleep(5000);
        this.driver.findElement(By.xpath("//span[contains(normalize-space(),'View all')]/..")).click();
        Thread.sleep(5000);
        Map<String,String> districtData= new HashMap<>();
        List<WebElement> districtList= this.driver.findElements(By.xpath("//div[@class='district-bar-left']/child::div/div/h5"));
        List<WebElement> districtRecoveredList= this.driver.findElements(By.xpath("//div[@class='district-bar-left']/child::div/div/h2"));
        for(int index=0;index<districtList.size();index++){

            districtData.put(districtList.get(index).getText(),districtRecoveredList.get(index).getText().replace(",",""));
//            districtData.put("Recovered",districtRecoveredList.get(index).getText().replace(",",""));
//            districtDataMap.add(districtData);

        }
//        System.out.printlndistrictDataMap);
        return districtData;
    }

}
