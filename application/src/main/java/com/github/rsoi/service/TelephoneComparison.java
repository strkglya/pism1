package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TelephoneComparison implements CommandLineRunner {

    public static void main(String [] args){
        SpringApplication.run(TelephoneComparison.class, args);
    }
    @Override
    public void run(String... args) {

        TelephoneData xiaomi = new TelephoneData("Xiaomi Redmi Note 10 Pro",730,6.7, 8,true );
        TelephoneData samsung = new TelephoneData("Samsung galaxy S20",1300,6.2, 8,true );
        TelephoneData iPhone = new TelephoneData("Apple Iphone 13 Pro",2250,6.1, 4,false );
        TelephoneData huawei = new TelephoneData("Huawei P40 lite",670, 6.4, 6, false);
        TelephoneData honor = new TelephoneData("HONOR 70",1299, 6.7, 8, false);

        List<TelephoneData> telephonesArrayList = new ArrayList<>();

        telephonesArrayList.add(xiaomi);
        telephonesArrayList.add(samsung);
        telephonesArrayList.add(iPhone);
        telephonesArrayList.add(huawei);
        telephonesArrayList.add(honor);

        Scanner scanner = new Scanner(System.in);
        int menuNumber = 0;
        String stringToCheck;

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
                    for (TelephoneData phone: telephonesArrayList){
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

                    for (TelephoneData telephoneData : telephonesArrayList) {
                        if (telephoneData.getPriceOfTheTelephone() > userMinPrice & telephoneData.getPriceOfTheTelephone() < userMaxPrice) {
                            telephoneData.setMatchCounter(telephoneData.getMatchCounter() + 1);
                        }
                        if (telephoneData.getSizeOfTheScreen().equals(userSizeOfScreen)) {
                            telephoneData.setMatchCounter(telephoneData.getMatchCounter() + 1);
                        }
                        if (telephoneData.getAmountOfRAM() == userRAM) {
                            telephoneData.setMatchCounter(telephoneData.getMatchCounter() + 1);
                        }
                        if (telephoneData.getSdCardIsAvailable() == userSDAvailable) {
                            telephoneData.setMatchCounter(telephoneData.getMatchCounter() + 1);
                        }
                    }

                    int maximum = telephonesArrayList.get(0).getMatchCounter();

                    for (TelephoneData telephoneData : telephonesArrayList) {
                        if (maximum < telephoneData.getMatchCounter())
                            maximum = telephoneData.getMatchCounter();
                    }

                    for (TelephoneData telephoneData : telephonesArrayList) {
                        if (telephoneData.getMatchCounter() == maximum) {
                            System.out.println("\nTake a look at the following option\n");
                            telephoneData.getData();
                        }
                    }
                case 3:
                    break;
            }
        }
    }
}
