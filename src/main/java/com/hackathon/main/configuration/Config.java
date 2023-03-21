package com.hackathon.main.configuration;


import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com/hackathon/main")
@PropertySources({@PropertySource("classpath:application.yml"), @PropertySource(value = "classpath:apiendpoints.yml", factory = YamlPropertySourceFactory.class)})
public class Config {


}
