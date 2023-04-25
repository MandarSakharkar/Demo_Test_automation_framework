package com.hackathon.main.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CommandLineArgCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String androidTest = context.getEnvironment().getProperty("androidTest");
        return androidTest != null && androidTest.equalsIgnoreCase("true");
    }
}

