package com.wendecator.restaurant.responses;

import com.wendecator.restaurant.util.Violation;

import java.util.ArrayList;
import java.util.List;

// FIXME: need to rename this

public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }
}
