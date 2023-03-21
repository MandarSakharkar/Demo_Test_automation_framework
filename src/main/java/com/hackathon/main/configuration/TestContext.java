package com.hackathon.main.configuration;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("storyContext")
public class TestContext {

    private Map<String, Object> scenarioContext;

    public TestContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public <T>T getContext(String key) {
        return (T) scenarioContext.get(key);
    }

    public Boolean isContains(String key) {
        return scenarioContext.containsKey(key);
    }

}


