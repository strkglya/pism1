package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneCatalog;

import java.util.ArrayList;
import java.util.Scanner;

import com.github.rsoi.domain.*;

public class TelephonesComparison {

    public static void main(String [] args){

        TelephoneCatalog xiaomi = new TelephoneCatalog("Xiaomi Redmi Note 10 Pro",730.0,6.67, 8,true );
        TelephoneCatalog samsung = new TelephoneCatalog("Samsung galaxy S20",1300.0,6.2, 8,true );
        TelephoneCatalog iPhone = new TelephoneCatalog("Apple Iphone 13 Pro",2250.0,6.1, 4,false );

        ArrayList<TelephoneCatalog> telephonesArrayList =  new ArrayList<TelephoneCatalog>();
        telephonesArrayList.add(xiaomi);
        telephonesArrayList.add(samsung);
        telephonesArrayList.add(iPhone);

        Scanner scanner = new Scanner(System.in);

        int menuNumber = 0;
        String stringToCheck = "";

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
            }
        }
    }
}
