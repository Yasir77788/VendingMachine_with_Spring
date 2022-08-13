package com.sg.assignment4.vendingmachine.service;

import com.sg.assignment4.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.assignment4.vendingmachine.dao.VendingMachinePersistenceExeption;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceExeption {

    }
}
