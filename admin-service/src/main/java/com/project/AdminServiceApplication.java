package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    /**
     * TODO: applicaiton.yml kontrol edilecek, portler ve db oluşturulacak.
     * TODO: postgresqlde db açılacak
     */
}