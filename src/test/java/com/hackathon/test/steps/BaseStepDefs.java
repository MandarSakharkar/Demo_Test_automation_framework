package com.hackathon.test.steps;

import com.hackathon.main.configuration.Config;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@CucumberContextConfiguration
@SpringBootTest(classes = Config.class)
public class BaseStepDefs {

    @Autowired
    protected Environment env;

}
