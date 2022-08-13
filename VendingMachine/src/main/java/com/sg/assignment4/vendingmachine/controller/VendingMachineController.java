package com.sg.assignment4.vendingmachine.controller;

import com.sg.assignment4.vendingmachine.dao.*;
import com.sg.assignment4.vendingmachine.dto.Item;
import com.sg.assignment4.vendingmachine.service.InsufficientFundException;
import com.sg.assignment4.vendingmachine.service.NoItemInventoryException;
import com.sg.assignment4.vendingmachine.service.VendingMachineService;
import com.sg.assignment4.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.assignment4.vendingmachine.view.UserIO;
import com.sg.assignment4.vendingmachine.view.UserIoConsoleImpl;
import com.sg.assignment4.vendingmachine.view.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

// controller class to coordinate communication between dao and view
@Controller
public class VendingMachineController {

    // declare references and variables
    private VendingMachineView view;
    private VendingMachineService service;
    private int userMenuSelection;
    private int moneyInput;
    private boolean keepGoing = true; // flag for while loop


    // DI, using constructor injection
    @Autowired
    public VendingMachineController(VendingMachineService service,VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    // run() method calls other controller-methods, and view-methods
    public void run() {
        try {
            while (keepGoing) {

                int listLength =   getAllItems().size(); // dynamic size of the arraylist of items in the stock

                // the displayed stock-items are updated dynamically to display the current available items
                view.displayItemsToConsumer(getAllItems());
                userMenuSelection = view.getUserMenuSelection(listLength); // get the user selection-number of the item

                if(userMenuSelection >= 0 && userMenuSelection <= listLength-1){ // checking for the right selection
                    checkItemWithZeroInventory(userMenuSelection);  // verify that the selected item is in the stock
                                                                    // otherwise, throw NoItemInventoryException
                    moneyInput = view.getMoneyAmount(); // then, get money amount entered by the user from the view

                    checkMoneyInputLessThanPrice(moneyInput, userMenuSelection); // verify that user money >= item-price
                                                                                 // otherwise, throw InsufficientFundException
                    calculateChange(moneyInput - getAllItems().get(userMenuSelection).getItemCost());
                    updateInventory(userMenuSelection); // update stock item count
                }
                else if(userMenuSelection == listLength){ // if user chooses to exit, exit
                    exitMessage();
                    keepGoing = false;
                    break;
                }
                else // user entered wrong item number... So, continue the loop
                    view.displaDisplayEnterCorrectSelection();
            }
        } catch (VendingMachinePersistenceExeption |NoItemInventoryException | InsufficientFundException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }


    // method to get all the current items in the stock (list).
    // It returns a list of the current Items
    private List<Item> getAllItems() throws VendingMachinePersistenceExeption {
        try {
            List<Item> listOfItemsFromFile = service.getAllItemsFromService(); // call service
            return listOfItemsFromFile;
        } catch (VendingMachinePersistenceExeption e) {
            keepGoing = false; // stop the while loop, if exception is thrown
            throw new VendingMachinePersistenceExeption("No item found: ");
        }
    }

    // method to validate the money entered against the prospective item
    // it calls the service layer method
    // It rethrows insufficient fund exception, if userMoney < selection price
    public void checkMoneyInputLessThanPrice(int userMoney, int selection)
            throws InsufficientFundException {
        try{
            service.processUserMoney(userMoney, selection);
        }catch(InsufficientFundException e){
            keepGoing = false; // stop the while loop
            throw new InsufficientFundException("Insufficient fund: "+ userMoney + " cents.");
        } catch (VendingMachinePersistenceExeption e) {
            System.out.println(e);
        }
    }

    // method to check if an item has zero inventory (out of stock)
    // It calls the service layer to handle to issue
    // If the item is out of stock, it rethrow NoItemInventoryException to the run method
    private void checkItemWithZeroInventory(int itemId)
            throws NoItemInventoryException, VendingMachinePersistenceExeption {
        try{
            service.checkItemStockLevel(itemId);
        }catch(NoItemInventoryException e){
            keepGoing = false;
            throw new NoItemInventoryException("Sorry, " + getAllItems().get(itemId).getItemName() + " is out of stock.");
        }
    }

    // Method to calculate the due change
    // it calls the service layer.
    // it returns a map of Integers (number of coins) and Coin names as strings
    private void calculateChange(int inputMoney)  {
        Map<Integer, String> changeMap = service.findChange(inputMoney);
        view.displayChange(changeMap);
    }

    // method to update item-count in the inventory
    // it takes the selection id as an identifier to decrement the item-count
    // it calls the service layer method updateItemList
    private void updateInventory(int userMenuSelection) throws VendingMachinePersistenceExeption {
        service.updateItemList(userMenuSelection);
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
