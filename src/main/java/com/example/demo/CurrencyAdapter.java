package com.example.demo;

public class CurrencyAdapter implements CurrencyConverter {
    private String currencySymbol; // Holds the currency symbol to be displayed

    public CurrencyAdapter(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @Override
    public double convertAmount(int amount) {
        if (currencySymbol.equals("USD")) {
            return amount * 1.1; // Assuming 1 USD = 1.1 original currency (replace with the actual rate)
        } else if (currencySymbol.equals("EUR")) {
            return amount * 0.9; // Assuming 1 EUR = 0.9 original currency (replace with the actual rate)
        } else {
            return amount; // Return the original amount if the currency is not supported
        }
    }
}
