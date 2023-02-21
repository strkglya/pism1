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

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getPriceOfTheTelephone()>userMinPrice & telephonesArrayList.get(index).getPriceOfTheTelephone()<userMaxPrice) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("price");
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                    }

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getSizeOfTheScreen().equals(userSizeOfScreen)) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("size of the screen");
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                    }

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getAmountOfRAM() == userRAM) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("amount of RAM");
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                    }

                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (telephonesArrayList.get(index).getSdCardIsAvailable() == userSDAvailable) {
                            telephonesArrayList.get(index).setMatchCounter(telephonesArrayList.get(index).getMatchCounter()+1);
                            System.out.println("SD");
                            System.out.println("xiaomi"+xiaomi.getMatchCounter());
                            System.out.println("samsung"+samsung.getMatchCounter());
                            System.out.println("iphone"+iPhone.getMatchCounter());
                        }
                    }

                    int maximum = telephonesArrayList.get(0).getMatchCounter();
                    for (int index = 0; index<telephonesArrayList.size(); index++) {
                        if (maximum < telephonesArrayList.get(index).getMatchCounter())
                            maximum = telephonesArrayList.get(index).getMatchCounter();
                    }
                    System.out.println("max is"+maximum);

                case 3:
                    break;
            }
        }
    }
}
