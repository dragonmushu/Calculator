package io.calculatorapi.operators;

import io.calculatorapi.enums.OperatorType;

public class OperatorFactory {
    public static Operator getOperator(OperatorType operator) {
        switch (operator) {
            case ADD: return new Add();
            case SUBTRACT: return new Subtract();
            case MULTIPLY: return new Multiply();
            case DIVIDE: return new Divide();
        }

        throw new UnsupportedOperationException();
    }
}
