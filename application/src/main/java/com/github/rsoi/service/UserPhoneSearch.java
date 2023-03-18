package com.github.rsoi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class UserPhoneSearch {

    private int userMinimumPrice = -1;
    private int userMaximumPrice = -1;
    private int userRAM = 0;
    private Double userSizeOfScreen = 0.0;
    private Boolean userSDAvailable = null;
    public void searchForPhone(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter minimum price");
        do {
            try {
                userMinimumPrice = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Integers only");
            }
        } while (userMinimumPrice < 0);

        System.out.println("Enter maximum price");
        do {
            try {
                userMaximumPrice = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Integers only");
            }
        } while (userMaximumPrice == 0);

        System.out.println("Enter preferred size of screen");
        do {
            try {
                userSizeOfScreen = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Screen size must be double!");
            }
        } while (userSizeOfScreen == 0);


        System.out.println("Enter preferred amount of RAM");
        do {
            try {
                userRAM = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Integers only");
            }
        } while (userRAM == 0);

        System.out.println("Is SD card needed?");
        do {
            try {
                String booleanHelper;
                booleanHelper = scanner.next();
                if (booleanHelper.equals("Yes") || booleanHelper.equals("yes")){
                    userSDAvailable = true;
                } else if (booleanHelper.equals("No") || booleanHelper.equals("no")){
                    userSDAvailable = false;
                }
            } catch (NumberFormatException e){
                System.out.println("Error! Write only Yes or No");
            }
        } while (userSDAvailable == null);
    }

    public int getUserMinimumPrice() {
        return userMinimumPrice;
    }

    public int getUserMaximumPrice() {
        return userMaximumPrice;
    }

    public int getUserRAM() {
        return userRAM;
    }

    public Double getUserSizeOfScreen() {
        return userSizeOfScreen;
    }

    public Boolean getUserSDAvailable() {
        return userSDAvailable;
    }
}
