package com.sg.assignment4.vendingmachine.dao;

import com.sg.assignment4.vendingmachine.dto.Item;
import com.sg.assignment4.vendingmachine.service.InsufficientFundException;
import com.sg.assignment4.vendingmachine.service.NoItemInventoryException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class VendingMachineDaoFileImpl implements VendingMachineDao{

    private final String STOCK_FILE;
    public static final String DELIMITER = ",";

    // Arraylist to hold the items
    List<Item> listOfAllItems = new ArrayList<>();


    // no-arg constructor
    public VendingMachineDaoFileImpl(){
        STOCK_FILE = "Stock.txt";
    }

    // Arg-contractor
    public VendingMachineDaoFileImpl(String vendingTextFile){
        STOCK_FILE = vendingTextFile;
    }

    // method to load the items from file into the arraylist
    private void loadItemsIntoList() throws VendingMachinePersistenceExeption {
        listOfAllItems.clear();
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STOCK_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceExeption(
                    "-_- Could not load stock data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent item unmarshalled

        Item currentItem;
        // Go through ITEM_FILE line by line, decoding each line into an
        // item object by calling the unmarshallStudent method.
        // Process while there are more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into an Item
            currentItem = unmarshallItem(currentLine);

            // add the current item to the arraylist
            listOfAllItems.add(currentItem);
        }
        // close scanner
        scanner.close();
    }

    private Item unmarshallItem(String itemAsText){
        // itemAsText is expecting a line read in from file.
        // split that line on the DELIMITER - which is a comma (,).
        // the array of Strings is stored in itemTokens.

        String[] itemTokens = itemAsText.split(DELIMITER);

        // itemName is in index 0 of the array.
        String itemName = itemTokens[0];

        // use itemName to create a new item object to satisfy
        // the requirements of the Item constructor.
        Item itemFromFile = new Item(itemName);

        // Index 1 - itemCost
        itemFromFile.setItemCost(Integer.parseInt(itemTokens[1]));

        // Index 2 - numOfItemsInInventory
        itemFromFile.setNumOfItemsInInventory(Integer.parseInt(itemTokens[2]));

        // return the item object, unmarshalled
        return itemFromFile;
    }

    private String marshallItem(Item anItem){
        // turn an item object into a line of text for the file.

        //item name
        String itemAsText = anItem.getItemName() + DELIMITER;;

        // item cost
        itemAsText += anItem.getItemCost() + DELIMITER;

        // inventory level
        itemAsText += anItem.getNumOfItemsInInventory();

        return itemAsText;
    }

    /**
     * Writes all items in the stock out to a STOCK_FILE.
     *
     * @throws VendingMachinePersistenceExeption if an error occurs writing to the file
     *
     */
    public void writeItem() throws VendingMachinePersistenceExeption {

        PrintWriter outputFile;

        try {
            outputFile = new PrintWriter(new FileWriter(STOCK_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceExeption(
                    "Could not save Item data.", e);
        }

        // Write out the item objects to the stock file.
        // get the list of items and iterate over them
        String itemAsText;

        for (Item currentItem : listOfAllItems) {
            // turn an item into a String
            itemAsText = marshallItem(currentItem); // marshalling the object to file format

            // write the item object to the file
            outputFile.println(itemAsText);
            // force PrintWriter to write line to the file
            outputFile.flush();
        }
        // Clean up
        outputFile.close();

    }

    // load items to the arraylist and return the list
    @Override
    public List<Item> loadItemsFormFileToList() throws VendingMachinePersistenceExeption {
        loadItemsIntoList();
        return listOfAllItems;
    }

    // if the item count is >= 0,
    // update or decrement the number of item stock level by one for each complete transaction
    @Override
    public void updateItemsInTheListInDao(int index) throws VendingMachinePersistenceExeption {
        if(listOfAllItems.get(index).getNumOfItemsInInventory() > 0) {
            listOfAllItems.get(index).setNumOfItemsInInventory(listOfAllItems.get(index).getNumOfItemsInInventory()-1);
            writeItem();
        }
    }

}
