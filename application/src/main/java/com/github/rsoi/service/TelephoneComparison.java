package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneCatalog;

import java.util.ArrayList;
import java.util.Scanner;

public class TelephoneComparison {

    public static void main(String [] args){

        TelephoneCatalog xiaomi = new TelephoneCatalog("Xiaomi Redmi Note 10 Pro",730,6.7, 8,true );
        TelephoneCatalog samsung = new TelephoneCatalog("Samsung galaxy S20",1300,6.2, 8,true );
        TelephoneCatalog iPhone = new TelephoneCatalog("Apple Iphone 13 Pro",2250,6.1, 4,false );

        ArrayList<TelephoneCatalog> telephonesArrayList = new ArrayList<>();
        telephonesArrayList.add(xiaomi);
        telephonesArrayList.add(samsung);
        telephonesArrayList.add(iPhone);

        Scanner scanner = new Scanner(System.in);
        int menuNumber = 0;
        String stringToCheck = "";

        UserPhoneData userPhoneData = new UserPhoneData();

        System.out.println("Hi! Let's choose a phone");
        while (menuNumber!=3){
            System.out.println("Enter 1 to view all options \nEnter 2 to enter your parameters \nEnter 3 to exit");
            stringToCheck = scanner.next();

            try {
                menuNumber = Integer.parseInt(stringToCheck);
            } catch (NumberFormatException e) {
                System.out.println("Error! Numbers only");
            }

            switch (menuNumber) {
                case 1:
                   for (TelephoneCatalog phone: telephonesArrayList){
                       phone.getData();
                   }
                   break;
                case 2:
                    userPhoneData.searchForPhone();

                    int userMinPrice = userPhoneData.getUserMinimumPrice();
                    int userMaxPrice = userPhoneData.getUserMaximumPrice();
                    Double userSizeOfScreen= userPhoneData.getUserSizeOfScreen();
                    int userRAM = userPhoneData.getUserRAM();
                    Boolean userSDAvailable = userPhoneData.getUserSDAvailable();

                    for (TelephoneCatalog telephoneCatalog : telephonesArrayList) {
                        if (telephoneCatalog.getPriceOfTheTelephone() > userMinPrice & telephoneCatalog.getPriceOfTheTelephone() < userMaxPrice) {
                            increaseCounter(telephoneCatalog);
                        }
                        if (telephoneCatalog.getSizeOfTheScreen().equals(userSizeOfScreen)) {
                            increaseCounter(telephoneCatalog);
                        }
                        if (telephoneCatalog.getAmountOfRAM() == userRAM) {
                            increaseCounter(telephoneCatalog);
                        }
                        if (telephoneCatalog.getSdCardIsAvailable() == userSDAvailable) {
                            increaseCounter(telephoneCatalog);
                        }
                    }

                    int maximum = telephonesArrayList.get(0).getMatchCounter();

                    for (TelephoneCatalog telephoneCatalog : telephonesArrayList) {
                        if (maximum < telephoneCatalog.getMatchCounter())
                            maximum = telephoneCatalog.getMatchCounter();
                    }

                    for (TelephoneCatalog telephoneCatalog : telephonesArrayList) {
                        if (telephoneCatalog.getMatchCounter() == maximum) {
                            System.out.println("Look at this option\n");
                            telephoneCatalog.getData();
                        }
                    }
                case 3:
                    break;
            }
        }
    }
    private static void increaseCounter(TelephoneCatalog telephoneCatalog) {
        telephoneCatalog.setMatchCounter(telephoneCatalog.getMatchCounter() + 1);
    }
}
