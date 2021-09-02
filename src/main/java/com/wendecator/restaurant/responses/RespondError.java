package com.wendecator.restaurant.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RespondError {
    public static ResponseEntity<Object> generateResponse(HttpStatus httpStatus, Boolean success, Object responseObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("error", responseObject);

        return new ResponseEntity<Object>(map, httpStatus);
    }

    public static ResponseEntity<Object> generateResponse(HttpStatus httpStatus, Boolean success, String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("error", message);

        return new ResponseEntity<Object>(map, httpStatus);
    }
}
