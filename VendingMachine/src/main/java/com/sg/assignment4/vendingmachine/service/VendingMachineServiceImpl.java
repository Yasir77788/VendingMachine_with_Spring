package com.sg.assignment4.vendingmachine.service;

import com.sg.assignment4.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.assignment4.vendingmachine.dao.VendingMachineDao;
import com.sg.assignment4.vendingmachine.dao.VendingMachinePersistenceExeption;
import com.sg.assignment4.vendingmachine.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ServiceLayer Implementor class
@Service
public class VendingMachineServiceImpl implements VendingMachineService{

    // declaring dao and auditDao
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;


    // Constructor injection
    @Autowired
    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;

    }
    // pass-through method that calls dao
    // this method calls the dao to get a list of all items in the current stock
    @Override
    public List<Item> getAllItemsFromService() {
        try{
            return dao.loadItemsFormFileToList();
        }catch (VendingMachinePersistenceExeption e){
            System.out.println(e);
            return null;
        }

    }

    // this method calls dao to compare user money-input with the item price in the stock
    // it throws InsufficientFundException back to the controller
    @Override
    public void processUserMoney(double userMoney, int selection) throws InsufficientFundException {
        if (userMoney < getAllItemsFromService().get(selection).getItemCost())
            throw new InsufficientFundException("Insufficient fund: "+ userMoney + " cents.");
    }

    // method to check if an item is out of stock.
    // It calls the Dao method to find out if the item is out of stock
    // If it is out, it throws NoItemInventoryException back to the controller
    @Override
    public void checkItemStockLevel(int itemId) throws NoItemInventoryException {

        if(getAllItemsFromService().get(itemId).getNumOfItemsInInventory() == 0){
            throw new NoItemInventoryException(getAllItemsFromService().get(itemId).getItemName() + " is out of stock");
        }

    }

    // update the stock count
    // calls the dao to do it
    @Override
    public void updateItemList(int itemId) throws VendingMachinePersistenceExeption {
        dao.updateItemsInTheListInDao(itemId);
    }


    // This method calculates change
    // it takes the difference between the customer's money and the item price
    // it returns a hashMap of Integer and String from the Coin enum
    @Override
    public Map<Integer, String> findChange(int inputMoney)  {

        // define a hashMap to intercept the number of denominations and coin names
        Map<Integer, String> coinMap = new HashMap<>();

        int numPennies = inputMoney; // assign the due change number of pennies or cents
        int numDimes = 0;
        int numNickels = 0;
        int numQuarters = 0;

        numQuarters = Coin.QUARTER.toDenomination(numPennies); // convert the pennies to num of quarters
        coinMap.put(numQuarters, Coin.QUARTER.name()); // put the result in the map

        numPennies -= numQuarters * Coin.QUARTER.denomValue(); // subtract numQuarters (in pennies) from the original numPennies.
        numDimes = Coin.DIME.toDenomination(numPennies); // convert the pennies to coin
        coinMap.put(numDimes, Coin.DIME.name());

        numPennies -= numDimes * Coin.DIME.denomValue(); // repeating the process above with Dimes
        numNickels = Coin.NICKEL.toDenomination(numPennies);
        coinMap.put(numNickels, Coin.NICKEL.name());

        numPennies -= numNickels*Coin.NICKEL.denomValue();
        coinMap.put(numPennies, Coin.PENNY.name());

        return coinMap;
    }


}
