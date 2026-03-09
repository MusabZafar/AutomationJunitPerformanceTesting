package org.softwareTesting.dataDrivenTesting;

public class Money {

    private int amount;
    private String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must be the same to add");
        }
        return new Money(this.amount + other.amount, this.currency);
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currencies must be the same to subtract");
        }
        return new Money(this.amount - other.amount, this.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}