package io.calculatorapi;

import io.calculatorapi.enums.OperatorType;
import io.calculatorapi.services.CalculatorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    public void setup() {
        calculatorService = new CalculatorService();
    }

    @ParameterizedTest(name = "{index} => firstOperand={0}, secondOperand={1}, value={2}")
    @CsvSource({
            "10, 10, 20",
            "-10, -10, -20",
            "-10, 10, 0"
    })
    public void testAddNumbers(Double firstOperand, Double secondOperand, Double value) {
        assertEquals(calculatorService.evaluate(OperatorType.ADD, firstOperand, secondOperand).value, value);
    }

    @ParameterizedTest(name = "{index} => firstOperand={0}, secondOperand={1}, value={2}")
    @CsvSource({
            "10, 10, 0",
            "-10, -10, 0",
            "-10, 10, -20"
    })
    public void testSubtractNumbers(Double firstOperand, Double secondOperand, Double value) {
        assertEquals(calculatorService.evaluate(OperatorType.SUBTRACT, firstOperand, secondOperand).value, value);
    }

    @ParameterizedTest(name = "{index} => firstOperand={0}, secondOperand={1}, value={2}")
    @CsvSource({
            "10, 10, 100",
            "-10, -10, 100",
            "-10, 10, -100"
    })
    public void testMultiplyNumbers(Double firstOperand, Double secondOperand, Double value) {
        assertEquals(calculatorService.evaluate(OperatorType.MULTIPLY, firstOperand, secondOperand).value, value);
    }

    @ParameterizedTest(name = "{index} => firstOperand={0}, secondOperand={1}, value={2}")
    @CsvSource({
            "10, 10, 1",
            "-10, -10, 1",
            "-10, 10, -1",
            "0, 10, 0"
    })
    public void testDivideNumbers(Double firstOperand, Double secondOperand, Double value) {
        assertEquals(calculatorService.evaluate(OperatorType.DIVIDE, firstOperand, secondOperand).value, value);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(java.lang.ArithmeticException.class, () -> calculatorService.evaluate(OperatorType.DIVIDE, 10.0, 0.0));
    }
}
