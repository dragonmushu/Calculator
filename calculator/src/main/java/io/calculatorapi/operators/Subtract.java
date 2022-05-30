package io.calculatorapi.operators;

import java.util.List;

public class Subtract extends BaseOperator {

    @Override
    public Double Evaluate(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }
}
