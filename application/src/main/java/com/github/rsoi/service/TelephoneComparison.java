package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneCatalog;

import java.util.ArrayList;
import java.util.Scanner;

public class TelephoneComparison {

    public static void main(String [] args){

        TelephoneCatalog xiaomi = new TelephoneCatalog("Xiaomi Redmi Note 10 Pro",730.0,6.7, 8,true );
        TelephoneCatalog samsung = new TelephoneCatalog("Samsung galaxy S20",1300.0,6.2, 8,true );
        TelephoneCatalog iPhone = new TelephoneCatalog("Apple Iphone 13 Pro",2250.0,6.1, 4,false );

        ArrayList<TelephoneCatalog> telephonesArrayList =  new ArrayList<TelephoneCatalog>();
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

                    int matchCounter = 0;

                    Double userMinPrice = userPhoneData.getUserMaximumPrice();
                    Double userMaxPrice = userPhoneData.getUserMinimumPrice();
                    Double userSizeOfScreen= userPhoneData.getUserSizeOfScreen();
                    int userRAM = userPhoneData.getUserRAM();
                    Boolean userSDAvailable = userPhoneData.getUserSDAvailable();

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getAmountOfRAM() == userRAM) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                    }

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getSizeOfTheScreen() == userSizeOfScreen) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                            }
                    }

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getSdCardIsAvailable() == userSDAvailable) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                    }
                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getPriceOfTheTelephone()>userMinPrice && telephonesArrayList.get(index).getPriceOfTheTelephone()<userMaxPrice) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                }
                case 3:
                    break;
            }
        }
    }
}
