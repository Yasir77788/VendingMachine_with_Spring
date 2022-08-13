package com.sg.assignment4.vendingmachine.service;

public class InsufficientFundException extends Exception{
    public InsufficientFundException(String message) {
        super(message);
    }

    public InsufficientFundException(String message,
                                     Throwable cause) {
        super(message, cause);
    }
}
