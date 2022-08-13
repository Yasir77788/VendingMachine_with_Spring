package com.sg.assignment4.vendingmachine.dao;

import com.sg.assignment4.vendingmachine.dto.Item;
import com.sg.assignment4.vendingmachine.service.InsufficientFundException;
import com.sg.assignment4.vendingmachine.service.NoItemInventoryException;

import java.util.List;

public interface VendingMachineDao {

    //boolean processMoneyEntered(double userMoney, int selection) throws InsufficientFundException;

    //void checkItemLevelInTheStock(int itemId) throws NoItemInventoryException;

    List<Item> loadItemsFormFileToList() throws VendingMachinePersistenceExeption;

    void updateItemsInTheListInDao(int i) throws VendingMachinePersistenceExeption;
    public void writeItem() throws VendingMachinePersistenceExeption;
}
