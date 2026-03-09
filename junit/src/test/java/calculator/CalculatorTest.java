package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwareTesting.calculator.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorTest.class);
    private Calculator calculator;

    @BeforeEach
    void setUp() {

        calculator = new Calculator();
    }

    @Test
    void testAddition() {
        int result = calculator.add(1, 2);
        logger.info("Addition test result: {}", result);
        assertEquals(3, result);
    }

    @Test
    void testSubtraction() {
        int result = calculator.subtract(5, 3);
        logger.info("Subtraction test result: {}", result);
        assertEquals(2, result);
    }

    @Test
    void testMultiplication() {
        int result = calculator.multiply(3, 4);
        logger.info("Multiplication test result: {}", result);
        assertEquals(12, result);
    }

    @Test
    void testDivision() {
        int result = calculator.divide(6, 2);
        logger.info("Division test result: {}", result);
        assertEquals(3, result);
    }

    @Test
    void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculator.divide(6, 0));
        logger.error("Error during division by zero: {}", exception.getMessage());
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "5, 3, 8",
            "0, 0, 0",
            "-1, -1, -2"
    })
    void testAdditionWithParameters(int a, int b, int expected) {
        int result = calculator.add(a, b);
        logger.info("Addition with parameters: {} + {} = {}", a, b, result);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1, 1",
            "3, 5, -2",
            "0, 0, 0",
            "-1, -1, 0"
    })
    void testSubtractionWithParameters(int a, int b, int expected) {
        int result = calculator.subtract(a, b);
        logger.info("Subtraction with parameters: {} - {} = {}", a, b, result);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 6",
            "5, 5, 25",
            "0, 10, 0",
            "-2, -2, 4"
    })
    void testMultiplicationWithParameters(int a, int b, int expected) {
        int result = calculator.multiply(a, b);
        logger.info("Multiplication with parameters: {} * {} = {}", a, b, result);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "6, 3, 2",
            "9, 3, 3",
            "0, 1, 0",
            "-6, -3, 2"
    })
    void testDivisionWithParameters(int a, int b, int expected) {
        int result = calculator.divide(a, b);
        logger.info("Division with parameters: {} / {} = {}", a, b, result);
        assertEquals(expected, result);
    }
}