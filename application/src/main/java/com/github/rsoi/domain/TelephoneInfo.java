package com.github.rsoi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Data
@NoArgsConstructor
@Table(name = "telephones")
public class TelephoneInfo {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id")
    Long id;

    @Column(name = "name")  private String nameOfTheTelephone;
    @Column(name = "price") private int priceOfTheTelephone;
    @Column(name = "size") private Double sizeOfTheScreen;
    @Column(name = "ram") private int amountOfRAM;
    @Column(name = "sd") private Boolean sdCardIsAvailable;

    private int matchCounter = 0;
    public TelephoneInfo(String nameOfTheTelephone, int priceOfTheTelephone, Double sizeOfTheScreen, int amountOfRAM, Boolean sdCardIsAvailable){
        this.nameOfTheTelephone = nameOfTheTelephone;
        this.priceOfTheTelephone = priceOfTheTelephone;
        this.sizeOfTheScreen = sizeOfTheScreen;
        this.amountOfRAM = amountOfRAM;
        this.sdCardIsAvailable = sdCardIsAvailable;
    }
}
