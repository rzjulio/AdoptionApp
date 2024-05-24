package org.expeditors.mexicoapps.adoptionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdoptionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoptionAppApplication.class, args);
//        InitDB.initSchema();
//        InitDB.initData();
    }

}
