package com.hackathon.main.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackathon.main.configuration.RestParameters;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class JsonUtils {

    @Autowired
    RestParameters restParameters;

    String defaultRequestJsonPath = "src/test/resources/requests/%s.json";
    String defaultRequestPojoPath = "classpath:requestPojos/";
    String valueToSet = "";
    JsonNode jsonObject;

    public void updateJsonRequest(List<Map<String, String>> requestJsonValuesMap) throws IOException {
        String request = readJsonAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        jsonObject = objectMapper.readTree(request);
        for (int i = 0; i < requestJsonValuesMap.size(); i++) {
            for (String key : requestJsonValuesMap.get(i).keySet()) {
                valueToSet = requestJsonValuesMap.get(i).get(key);
                if (jsonObject.has(key)) {
                    ((ObjectNode) jsonObject).put(key, valueToSet);
                }
            }
        }
        restParameters.setRequest(objectMapper.writeValueAsString(jsonObject));
    }

    public void setRequestJsonPath(String endPoint) {
        restParameters.setRequestJsonPath(String.format(defaultRequestJsonPath, endPoint));
    }

    private String readJsonAsString() throws IOException {
        return new String(Files.readAllBytes(Paths.get(restParameters.getRequestJsonPath())));
    }

    public void validateJsonResponse(List<Map<String, String>> responseJsonValuesMap) throws IOException {
        String response = restParameters.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 0; i < responseJsonValuesMap.size(); i++) {
            for (String key : responseJsonValuesMap.get(i).keySet()) {
                String expectedValue = responseJsonValuesMap.get(i).get(key);
                List<String> nodeValuesList = new ArrayList<>();
                JsonNode rootNode = objectMapper.readTree(response);
                rootNode.elements().forEachRemaining(x -> {
                            if (x.isArray()) {
                                x.forEach(a -> nodeValuesList.addAll(a.findValuesAsText(a.textValue())));
                            }
                            nodeValuesList.addAll(x.findValuesAsText(key));
                        }
                );
//                rootNode.elements().forEachRemaining(element -> nodeValuesList.addAll(element.findValuesAsText(key)));
                Assert.assertTrue("The values are not as expected: " + "Expected Result: " + expectedValue + " Actual Result: " + nodeValuesList, nodeValuesList.contains(expectedValue));
            }
        }
    }

    public Map<String, String> fetchJsonResponseString(String covidTopState, String category) throws JsonProcessingException {
        String response = restParameters.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> districtMap = new HashMap<>();
        JsonNode rootNode = objectMapper.readTree(response);
//        rootNode=rootNode.get(covidTopState).get("districtData");
//        while (rootNode.hasNext()) {
//            JsonNode key = jsonNodeIterator.next();
//            districtMap.put(key.textValue(), key.get(category).textValue());
//        }
        return districtMap;
    }
}
