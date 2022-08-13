package com.sg.assignment4.vendingmachine.dao;

public class VendingMachinePersistenceExeption extends Exception{

    public VendingMachinePersistenceExeption(String message) {
        super(message);
    }

    public VendingMachinePersistenceExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
