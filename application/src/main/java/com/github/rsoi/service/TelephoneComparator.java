package com.github.rsoi.service;

import com.github.rsoi.domain.TelephoneInfo;
import com.github.rsoi.repository.TelephoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelephoneComparator {
    private final TelephoneRepository telephoneRepository;
    public List<TelephoneInfo> searchForPhone(int ram, double size, String sdCard, int minPrice, int maxPrice) {
        List<TelephoneInfo> telephones = new ArrayList<>();
        for (TelephoneInfo telephoneInfo : telephoneRepository.findAll()) {
            if (telephoneInfo.getPriceOfTheTelephone() > minPrice & telephoneInfo.getPriceOfTheTelephone() < maxPrice) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getSizeOfTheScreen().equals(size)) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            if (telephoneInfo.getAmountOfRAM() == ram) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
            boolean userSD = sdCard.equals("Yes");
            if (telephoneInfo.getSdCardIsAvailable() == userSD) {
                telephoneInfo.setMatchCounter(telephoneInfo.getMatchCounter() + 1);
            }
        }

        int maximum = telephoneRepository.findAll().get(0).getMatchCounter();

        for (TelephoneInfo telephoneInfo : telephoneRepository.findAll()) {
            if (maximum < telephoneInfo.getMatchCounter())
                maximum = telephoneInfo.getMatchCounter();
        }

        for (TelephoneInfo telephoneInfo : telephoneRepository.findAll()) {
            if (telephoneInfo.getMatchCounter() == maximum) {
                telephones.add(telephoneInfo);
            }
        }
    return telephones;
    }
}
