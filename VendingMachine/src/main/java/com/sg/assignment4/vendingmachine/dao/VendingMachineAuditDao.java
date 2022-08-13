package com.sg.assignment4.vendingmachine.dao;

public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceExeption;
}
