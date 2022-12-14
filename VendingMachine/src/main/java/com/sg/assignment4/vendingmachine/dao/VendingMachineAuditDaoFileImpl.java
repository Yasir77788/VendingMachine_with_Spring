package com.sg.assignment4.vendingmachine.dao;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
@Component
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao{

    public static final String AUDIT_FILE = "audit.txt";

    public void writeAuditEntry(String entry) throws VendingMachinePersistenceExeption {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingMachinePersistenceExeption("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
