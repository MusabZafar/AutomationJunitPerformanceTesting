package dataDrivenTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwareTesting.dataDrivenTesting.Money;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    private static final Logger logger = LoggerFactory.getLogger(MoneyTest.class);
    private Money money;

    @BeforeEach
    void setUp() {

        money = new Money(100, "USD");
    }

    @Test
    void testAdd() {
        Money moneyToAdd = new Money(50, "USD");
        Money result = money.add(moneyToAdd);
        logger.info("Addition result: {}", result);
        assertEquals("150 USD", result.toString());
    }

    @Test
    void testAddDifferentCurrencies() {
        Money moneyToAdd = new Money(50, "EUR");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> money.add(moneyToAdd));
        logger.error("Error during addition with different currencies: {}", exception.getMessage());
        assertEquals("Currencies must be the same to add", exception.getMessage());
    }

    @Test
    void testSubtract() {
        Money moneyToSubtract = new Money(30, "USD");
        Money result = money.subtract(moneyToSubtract);
        logger.info("Subtraction result: {}", result);
        assertEquals("70 USD", result.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "100, USD, 50, USD, 150, USD",
            "200, USD, 50, USD, 250, USD",
            "500, EUR, 100, EUR, 600, EUR"
    })
    void testAddParameterized(int amount1, String currency1, int amount2, String currency2, int expectedAmount, String expectedCurrency) {
        Money firstMoney = new Money(amount1, currency1);
        Money secondMoney = new Money(amount2, currency2);
        
        logger.info("Testing addition with money: {} + {}", firstMoney, secondMoney);
        if (firstMoney.getCurrency().equals(secondMoney.getCurrency())) {
            Money result = firstMoney.add(secondMoney);
            logger.info("Addition result: {}", result);
            assertEquals(expectedAmount, result.getAmount());
            assertEquals(expectedCurrency, result.getCurrency());
        } else {
            assertThrows(IllegalArgumentException.class, () -> firstMoney.add(secondMoney));
            logger.error("Currencies are different, cannot add {} and {}", currency1, currency2);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "100, USD, 50, USD, 50, USD",
            "200, USD, 100, USD, 100, USD",
            "500, EUR, 100, EUR, 400, EUR"
    })
    void testSubtractParameterized(int amount1, String currency1, int amount2, String currency2, int expectedAmount, String expectedCurrency) {
        Money firstMoney = new Money(amount1, currency1);
        Money secondMoney = new Money(amount2, currency2);

        logger.info("Testing subtraction with money: {} - {}", firstMoney, secondMoney);
        if (firstMoney.getCurrency().equals(secondMoney.getCurrency())) {
            Money result = firstMoney.subtract(secondMoney);
            logger.info("Subtraction result: {}", result);
            assertEquals(expectedAmount, result.getAmount());
            assertEquals(expectedCurrency, result.getCurrency());
        } else {
            assertThrows(IllegalArgumentException.class, () -> firstMoney.subtract(secondMoney));
            logger.error("Currencies are different, cannot subtract {} and {}", currency1, currency2);
        }
    }
}