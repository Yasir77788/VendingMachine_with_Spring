package com.sg.assignment4.vendingmachine.service;

import java.math.BigDecimal;

// Enum to represent Coins
public enum Coin {
    // declare coins and their denomination values
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    // declare variable
    private final int denomValue;

    // constructor
    Coin(int denomValue){

        this.denomValue = denomValue;
    }

    //accessor method
    public int denomValue()
    {
        return denomValue;
    }

    // method to convert pennies to number of respective coin
    int toDenomination(int numPennies)
    {
        return numPennies / denomValue;
    }

}
