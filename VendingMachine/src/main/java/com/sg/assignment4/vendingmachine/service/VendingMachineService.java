package com.sg.assignment4.vendingmachine.service;

import com.sg.assignment4.vendingmachine.dao.VendingMachinePersistenceExeption;
import com.sg.assignment4.vendingmachine.dto.Item;

import java.util.List;
import java.util.Map;

public interface VendingMachineService {
    List<Item> getAllItemsFromService() throws VendingMachinePersistenceExeption;

    void processUserMoney(double userMoney, int selection) throws InsufficientFundException, VendingMachinePersistenceExeption;

    void checkItemStockLevel(int itemId) throws NoItemInventoryException;

    Map<Integer, String> findChange(int inputMoney);

    void updateItemList(int i) throws VendingMachinePersistenceExeption;

}
