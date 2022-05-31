package io.calculatorapi.operators;

public class Subtract extends BaseOperator {

    @Override
    public Double Evaluate(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    @Override
    public String OperatorCharacter() {
        return "-";
    }
}
