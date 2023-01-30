package com.geoway.atlas.common.serverCenter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Transfromer {

    public String WebService2Json(WebService webservice) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(webservice);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public WebService Json2WebService(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            WebService webService=mapper.readValue(json, WebService.class);
            return webService;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
