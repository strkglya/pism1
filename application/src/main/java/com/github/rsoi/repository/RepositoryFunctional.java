package com.github.rsoi.repository;

import com.github.rsoi.domain.TelephoneInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class RepositoryFunctional {
    private final TelephoneRepository telephoneRepository;
    private int newPrice = -1;
    private int newRAM = 0;
    private Double newSizeOfScreen = 0.0;
    private Boolean newSDAvailable = null;

    @Transactional
    public void showTelephones(){
        var phones= telephoneRepository.findAll();
        for(TelephoneInfo telephoneInfo : phones)
        {
            telephoneInfo.getData();
        }
    }
    @Transactional
    public void addTelephone(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of the telephone");
        String newName = scanner.next();

        System.out.println("Enter price");
        do {
            try {
                newPrice = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Integers only");
            }
        } while (newPrice < 0);

        System.out.println("Enter size of screen");
        do {
            try {
                newSizeOfScreen = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Screen size must be double!");
            }
        } while (newSizeOfScreen == 0);


        System.out.println("Enter amount of RAM");
        do {
            try {
                newRAM = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e){
                System.out.println("Error! Integers only");
            }
        } while (newRAM == 0);

        System.out.println("Is SD card available?");
        do {
            try {
                String booleanHelper;
                booleanHelper = scanner.next();
                if (booleanHelper.equals("Yes") || booleanHelper.equals("yes")){
                    newSDAvailable = true;
                } else if (booleanHelper.equals("No") || booleanHelper.equals("no")){
                    newSDAvailable = false;
                }
            } catch (NumberFormatException e){
                System.out.println("Error! Write only Yes or No");
            }
        } while (newSDAvailable == null);
        telephoneRepository.save(new TelephoneInfo(newName,newPrice,newSizeOfScreen,newRAM,newSDAvailable));
        System.out.println("Telephone added successfully");
    }

    @Transactional
    public void deleteTelephone(){
        Scanner scan = new Scanner(System.in);
        String s;
        long id=-1;
        do {
            System.out.println("Enter id");
            s = scan.next();
            try {
                id = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Int values only");
            }
        } while (id < 0);
        telephoneRepository.deleteById(id);
        System.out.println("Phone was deleted successfully");
    }
    @Transactional
    public void updateTelephone(){
        Scanner scanner = new Scanner(System.in);
        long idOfTheTelephone=-1;

        do {
            System.out.println("Enter the ID of the telephone");
            String s = scanner.next();
            try {
                idOfTheTelephone = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Error! Integer only");
            }
        } while (idOfTheTelephone < 0);

        System.out.println("Enter name of the telephone");
        String newName = scanner.next();

        var update= telephoneRepository.findById(idOfTheTelephone).orElseThrow();
        update.setNameOfTheTelephone(newName);
        telephoneRepository.save(update);
        System.out.println("Name was updated successfully");
    }
}
