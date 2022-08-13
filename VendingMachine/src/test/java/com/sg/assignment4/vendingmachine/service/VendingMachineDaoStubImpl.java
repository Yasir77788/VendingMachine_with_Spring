package com.sg.assignment4.vendingmachine.service;

import com.sg.assignment4.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment4.vendingmachine.dao.VendingMachinePersistenceExeption;
import com.sg.assignment4.vendingmachine.dto.Item;

import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public VendingMachineDaoStubImpl() {
        Item testClone = new Item("Fanta");
        testClone.setItemCost(199);
        testClone.setNumOfItemsInInventory(20);
    }


    @Override
    public List<Item> loadItemsFormFileToList() throws VendingMachinePersistenceExeption {
        return null;
    }

    @Override
    public void updateItemsInTheListInDao(int i) throws VendingMachinePersistenceExeption {

    }

    @Override
    public void writeItem() throws VendingMachinePersistenceExeption {

    }
}
