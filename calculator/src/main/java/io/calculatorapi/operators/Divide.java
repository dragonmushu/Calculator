package io.calculatorapi.operators;

import java.util.List;

public class Divide extends BaseOperator {

    @Override
    public Double Evaluate(double firstOperand, double secondOperand) {
        return firstOperand / secondOperand;
    }
}
