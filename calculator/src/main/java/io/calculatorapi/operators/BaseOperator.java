package io.calculatorapi.operators;

import java.util.List;

public abstract class BaseOperator implements Operator {

    @Override
    public Double Evaluate(List<Double> operands) {
        Double result = operands.get(0);
        for (int i = 1; i < operands.size(); i++) {
            result = this.Evaluate(result, operands.get(i));
        }

        if (Double.isNaN(result) || Double.isInfinite(result)) {
            throw new ArithmeticException();
        }

        return result;
    }
}
