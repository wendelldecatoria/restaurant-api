package com.wendecator.restaurant.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RespondSuccess {

    public static ResponseEntity<Object> generateResponse(HttpStatus httpStatus, Boolean success, String message, Object responseObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", responseObject);
        map.put("message", message);
        map.put("success", success);

        return new ResponseEntity<Object>(map, httpStatus);
    }
}
