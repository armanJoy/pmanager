package com.coredevs.pmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmanagerApplication.class, args);
        System.out.println("P-Manager started...");
    }

}
