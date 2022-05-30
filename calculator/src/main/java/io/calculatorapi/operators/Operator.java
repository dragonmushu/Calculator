package io.calculatorapi.operators;

import java.util.List;

public interface Operator {
    public Double Evaluate(List<Double> operands);

    public Double Evaluate(double firstOperand, double secondOperand);
}
