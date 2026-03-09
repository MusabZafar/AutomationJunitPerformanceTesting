package bankAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwareTesting.bankAccount.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private static final Logger logger = LoggerFactory.getLogger(BankAccountTest.class);
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount(1000);  // Initial balance of 1000
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(500);
        logger.info("Deposit successful, new balance: {}", bankAccount.getBalance());
        assertEquals(1500, bankAccount.getBalance());
    }

    @Test
    void testDepositNegativeAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-100));
        logger.error("Error during deposit with negative amount: {}", exception.getMessage());
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    void testWithdraw() {
        bankAccount.withdraw(450);
        logger.info("Withdrawal successful, new balance: {}", bankAccount.getBalance());
        assertEquals(550, bankAccount.getBalance());
    }

    @Test
    void testWithdrawExceedingBalance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1500));
        logger.error("Error during withdrawal with insufficient funds: {}", exception.getMessage());
        assertEquals("Insufficient funds, no overdraft allowed", exception.getMessage());
    }

    @Test
    void testWithdrawNegativeAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));
        logger.error("Error during withdrawal with negative amount: {}", exception.getMessage());
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "500, 1500, 2000",
            "1000, 2000, 3000",
            "200, 500, 700"
    })
    void testDepositParameterized(double depositAmount, double initialBalance, double expectedBalance) {
        BankAccount tempAccount = new BankAccount(initialBalance);
        tempAccount.deposit(depositAmount);
        logger.info("Deposit of {} to account with initial balance {} results in balance {}", depositAmount, initialBalance, tempAccount.getBalance());
        assertEquals(expectedBalance, tempAccount.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "500, 500, 0, 0",
            "200, 500, 300, 300",
            "1000, 2000, 1000, 1000"
    })
    void testWithdrawParameterized(double withdrawalAmount, double initialBalance, double expectedBalanceAfterWithdraw, double finalBalance) {
        BankAccount tempAccount = new BankAccount(initialBalance);
        tempAccount.withdraw(withdrawalAmount);
        logger.info("Withdrawal of {} from account with initial balance {} results in balance {}", withdrawalAmount, initialBalance, tempAccount.getBalance());
        assertEquals(expectedBalanceAfterWithdraw, tempAccount.getBalance());
        assertEquals(finalBalance, tempAccount.getBalance());
    }
}