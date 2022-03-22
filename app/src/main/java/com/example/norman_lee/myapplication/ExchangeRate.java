package com.example.norman_lee.myapplication;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExchangeRate {

    private BigDecimal exchangeRate;
    private static String defaultRate = "2.95000";
    private static final int DEFAULT_PRECISION = 5;
    private int precision = DEFAULT_PRECISION;
    private MathContext mathContext;


    ExchangeRate(){
        exchangeRate = new BigDecimal(defaultRate);
        instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String exchangeRate){
        this.exchangeRate = new BigDecimal(exchangeRate);
        instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String home, String foreign) {

        instantiateMathContext(DEFAULT_PRECISION);
        //TODO 3.13a The constructor initializes exchangeRate by calculating the exchangeRate
        BigDecimal h = new BigDecimal(home);
        BigDecimal f = new BigDecimal(foreign);
        exchangeRate = f.divide(h);
    }

    BigDecimal getExchangeRate(){
        return exchangeRate;
    }

    BigDecimal calculateAmount(String foreign){
        //TODO 2.5a complete this method to return the amount
        return new BigDecimal(foreign).multiply(exchangeRate);
    }

    void setPrecision(int precision){
        this.precision = precision;
        instantiateMathContext(precision);
    }

    private void instantiateMathContext(int precision){
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
    }
}
