// Trainee Name: Yasir Hassan
// Assignment 3 - Vending Machine
package com.sg.assignment4.vendingmachine;

import com.sg.assignment4.vendingmachine.controller.VendingMachineController;
import com.sg.assignment4.vendingmachine.dao.*;
import com.sg.assignment4.vendingmachine.dao.*;
import com.sg.assignment4.vendingmachine.service.VendingMachineService;
import com.sg.assignment4.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.assignment4.vendingmachine.view.UserIO;
import com.sg.assignment4.vendingmachine.view.UserIoConsoleImpl;
import com.sg.assignment4.vendingmachine.view.VendingMachineView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceExeption {

        // using java annotations for spring container config
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.sg.assignment4.vendingmachine");
        appContext.refresh();

        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();
    }

}
