package io.calculatorapi.services;

import io.calculatorapi.dto.ResultDto;
import io.calculatorapi.enums.OperatorType;
import io.calculatorapi.models.Operation;

import java.util.ArrayList;
import java.util.List;

public class CalculatorService {
    private List<Operation> operations;

    public CalculatorService() {
        this.operations = new ArrayList<Operation>();
    }

    public ResultDto evaluate(OperatorType operator, Double firstOperand, Double secondOperand) {
        Operation operation = new Operation(operator, firstOperand, secondOperand);
        this.operations.add(operation);

        return toResultDto(operation);
    }

    public List<ResultDto> getAuditLogs() {
        return this.toAuditLogs();
    }

    private List<ResultDto> toAuditLogs() {
        List<ResultDto> auditLogs = new ArrayList<ResultDto>();
        for (Operation operation: operations) {
            auditLogs.add(toResultDto(operation));
        }

        return auditLogs;
    }

    private ResultDto toResultDto(Operation operation) {
        ResultDto result = new ResultDto();
        result.expression = operation.toExpression();
        result.value = operation.getResult();

        return result;
    }

}
