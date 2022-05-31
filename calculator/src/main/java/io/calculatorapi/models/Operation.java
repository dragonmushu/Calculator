package io.calculatorapi.models;

import io.calculatorapi.enums.OperatorType;
import io.calculatorapi.operators.Operator;
import io.calculatorapi.operators.OperatorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operation {
    private OperatorType operatorType;
    private Operator operator;
    private List<Double> operand;
    private Double result;

    public Operation(OperatorType operatorType, double firstOperand, double secondOperand) {
        this(operatorType, Arrays.asList(firstOperand, secondOperand));
    }

    public Operation(OperatorType operatorType, List<Double> operand) {
        this.operand = operand;
        this.operatorType = operatorType;
        this.operator = OperatorFactory.getOperator(operatorType);

        this.result = evaluate();
    }

    public double getResult() {
        return this.result;
    }

    public String toString() {
        return null;
    }

    public String toExpression() {
        List<String> operands = new ArrayList<String>();
        for (Double op: this.operand) {
            operands.add(String.valueOf(op));
        }
        return String.join(" " + operator.OperatorCharacter() + " ", operands);
    }

    private Double evaluate() {
        return this.operator.Evaluate(this.operand);
    }
}
