package com.sg.assignment4.vendingmachine.view;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
@Component
public class UserIoConsoleImpl implements UserIO{

    final private Scanner console = new Scanner(System.in);


    @Override
    public void print(String msg) {
        System.out.println(msg);
    }


    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    // view method to read an int
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt
                String stringValue = this.readString(msgPrompt);

                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {

                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    // overloaded readInt method
    @Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

}
