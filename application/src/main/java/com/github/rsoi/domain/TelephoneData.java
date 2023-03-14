package com.github.rsoi.domain;
public class TelephoneData {

    //vars
    private String nameOfTheTelephone;
    private int priceOfTheTelephone;
    private Double sizeOfTheScreen;
    private int amountOfRAM;
    private Boolean sdCardIsAvailable;

    private int matchCounter = 0;

    public TelephoneData(String nameOfTheTelephone, int priceOfTheTelephone, Double sizeOfTheScreen, int amountOfRAM, Boolean sdCardIsAvailable){
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

    public int getPriceOfTheTelephone() {
        return priceOfTheTelephone;
    }

    public void setPriceOfTheTelephone(int priceOfTheTelephone) {
        this.priceOfTheTelephone = priceOfTheTelephone;
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
    public int getMatchCounter() {
        return matchCounter;
    }

    public void setMatchCounter(int matchCounter) {
        this.matchCounter = matchCounter;
    }
}
