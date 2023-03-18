package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneData;

import java.util.List;

public class UserPhoneComparison {
    UserPhoneSearch userPhoneSearch = new UserPhoneSearch();
    public void searchForPhone(List<TelephoneData> telephonesArrayList){
        userPhoneSearch.searchForPhone();

        int userMinPrice = userPhoneSearch.getUserMinimumPrice();
        int userMaxPrice = userPhoneSearch.getUserMaximumPrice();
        Double userSizeOfScreen= userPhoneSearch.getUserSizeOfScreen();
        int userRAM = userPhoneSearch.getUserRAM();
        Boolean userSDAvailable = userPhoneSearch.getUserSDAvailable();

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
    }
}
