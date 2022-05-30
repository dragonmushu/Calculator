package io.calculatorapi.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperatorType {
    ADD,
    SUBTRACT,
    DIVIDE,
    MULTIPLY;

    public static OperatorType fromString(String operator) {
        String upper = operator.toUpperCase();
        return valueOf(upper);
    }
}
