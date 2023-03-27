package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPhoneComparison {
    private final UserPhoneSearch userPhoneSearch;
    public List<TelephoneInfo> searchForPhone(List<TelephoneInfo> telephonesArrayList){
        userPhoneSearch.searchForPhone();

        int userMinPrice = userPhoneSearch.getUserMinimumPrice();
        int userMaxPrice = userPhoneSearch.getUserMaximumPrice();
        Double userSizeOfScreen= userPhoneSearch.getUserSizeOfScreen();
        int userRAM = userPhoneSearch.getUserRAM();
        Boolean userSDAvailable = userPhoneSearch.getUserSDAvailable();

        for (TelephoneInfo telephoneInfo : telephonesArrayList) {
            if (telephoneInfo.getPriceOfTheTelephone() > userMinPrice & telephoneInfo.getPriceOfTheTelephone() < userMaxPrice) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getSizeOfTheScreen().equals(userSizeOfScreen)) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getAmountOfRAM() == userRAM) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getSdCardIsAvailable() == userSDAvailable) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
        }

        int maximum = telephonesArrayList.get(0).getMatchCounter();

        for (TelephoneInfo telephoneInfo : telephonesArrayList) {
            if (maximum < telephoneInfo.getMatchCounter())
                maximum = telephoneInfo.getMatchCounter();
        }

        for (TelephoneInfo telephoneInfo : telephonesArrayList) {
            if (telephoneInfo.getMatchCounter() == maximum) {
                System.out.println("\nTake a look at the following option\n");
                telephoneInfo.getData();
            }
        }
        return telephonesArrayList;
    }
}
