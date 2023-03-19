package com.github.rsoi;
import com.github.rsoi.domain.TelephoneInfo;
import com.github.rsoi.repository.RepositoryFunctional;
import com.github.rsoi.repository.TelephoneRepository;
import com.github.rsoi.service.UserPhoneComparison;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor

public class TelephoneMain implements CommandLineRunner {
    private final UserPhoneComparison userPhoneComparison;
    private final RepositoryFunctional repositoryFunctional;
    private final TelephoneRepository telephoneRepository;
    public static void main(String [] args){
        SpringApplication.run(TelephoneMain.class, args);
    }

    @Override
    public void run(String... args) {

        TelephoneInfo xiaomi = new TelephoneInfo("Xiaomi Redmi Note 10 Pro",730,6.7, 8,true );
        TelephoneInfo samsung = new TelephoneInfo("Samsung galaxy S20",1300,6.2, 8,true );
        TelephoneInfo iPhone = new TelephoneInfo("Apple Iphone 13 Pro",2250,6.1, 4,false );
        TelephoneInfo huawei = new TelephoneInfo("Huawei P40 lite",670, 6.4, 6, false);
        TelephoneInfo honor = new TelephoneInfo("HONOR 70",1299, 6.7, 8, false);

        List<TelephoneInfo> telephonesArrayList = new ArrayList<>();

        telephonesArrayList.add(xiaomi);
        telephonesArrayList.add(samsung);
        telephonesArrayList.add(iPhone);
        telephonesArrayList.add(huawei);
        telephonesArrayList.add(honor);


        Scanner scanner = new Scanner(System.in);
        int menuNumber = 0;
        String stringToCheck;

        System.out.println("Hi! Let's choose a phone");

        while (menuNumber!=5){
            System.out.println("Enter 1 to view all options \nEnter 2 to enter your parameters \nEnter 3 to add phone\nEnter 4 to delete phone\nEnter 5 to exit");
            stringToCheck = scanner.next();
            try {
                menuNumber = Integer.parseInt(stringToCheck);
            } catch (NumberFormatException e) {
                System.out.println("Error! Numbers only");
            }

            switch (menuNumber) {
                case 1:
                    repositoryFunctional.showTelephones();
                    break;
                case 2:
                    userPhoneComparison.searchForPhone(telephonesArrayList);
                case 3:
                    repositoryFunctional.addTelephone();
                    break;
                case 4:
                    repositoryFunctional.deleteTelephone();
                    break;
                case 5:
                    break;
            }
        }
    }
}
