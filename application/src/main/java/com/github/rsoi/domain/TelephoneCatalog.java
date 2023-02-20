package com.github.rsoi.domain;

import java.util.ArrayList;
import java.util.Scanner;

public class TelephoneCatalog {

    //vars
    private String nameOfTheTelephone;
    private Double priceOfTheTelephone;
    private Double minimumPrice;
    private Double maximumPrice;
    private Double sizeOfTheScreen;
    private int amountOfRAM;
    private Boolean sdCardIsAvailable;

    public TelephoneCatalog(String nameOfTheTelephone, Double priceOfTheTelephone, Double sizeOfTheScreen, int amountOfRAM, Boolean sdCardIsAvailable){
        this.nameOfTheTelephone = nameOfTheTelephone;
        this.priceOfTheTelephone = priceOfTheTelephone;
        this.sizeOfTheScreen = sizeOfTheScreen;
        this.amountOfRAM = amountOfRAM;
        this.sdCardIsAvailable = sdCardIsAvailable;
    }

    public void getData(){
        System.out.println("Name of the telephone is " + nameOfTheTelephone);
        System.out.println("Price of the telephone is " + priceOfTheTelephone);
        System.out.println("Size of the screen is " + sizeOfTheScreen);
        System.out.println("Amount of RAM is " + amountOfRAM);

        if (sdCardIsAvailable){
            System.out.println("SD card is available");
        }
        else {
            System.out.println("SD card is not available");
        }
        System.out.println("-----------------------------");
    }












    public String getNameOfTheTelephone() {
        return nameOfTheTelephone;
    }

    public void setNameOfTheTelephone(String nameOfTheTelephone) {
        this.nameOfTheTelephone = nameOfTheTelephone;
    }

    public Double getPriceOfTheTelephone() {
        return priceOfTheTelephone;
    }

    public void setPriceOfTheTelephone(Double priceOfTheTelephone) {
        this.priceOfTheTelephone = priceOfTheTelephone;
    }

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(Double maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Double getSizeOfTheScreen() {
        return sizeOfTheScreen;
    }

    public void setSizeOfTheScreen(Double sizeOfTheScreen) {
        this.sizeOfTheScreen = sizeOfTheScreen;
    }

    public int getAmountOfRAM() {
        return amountOfRAM;
    }

    public void setAmountOfRAM(int amountOfRAM) {
        this.amountOfRAM = amountOfRAM;
    }

    public Boolean getSdCardIsAvailable() {
        return sdCardIsAvailable;
    }

    public void setSdCardIsAvailable(Boolean sdCardIsAvailable) {
        this.sdCardIsAvailable = sdCardIsAvailable;
    }
}
