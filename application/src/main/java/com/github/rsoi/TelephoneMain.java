package com.github.rsoi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class TelephoneMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TelephoneMain.class, args);
    }

    @Override
    public void run(String... args) {

    }
}




