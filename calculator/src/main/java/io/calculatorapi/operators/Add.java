package io.calculatorapi.operators;

public class Add extends BaseOperator {

    @Override
    public Double Evaluate(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    @Override
    public String OperatorCharacter() {
        return "+";
    }
}
