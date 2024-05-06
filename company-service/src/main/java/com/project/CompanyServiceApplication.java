package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(CompanyServiceApplication.class, args);

        /**
         * Yapılacaklar;
         * company aktifleşmesini manager ve autha da rabbitle gönder.
         * yetkilendirme admin yapması lazım yine 403 hatası verdi? permtiall diyince hata gitti.
         * pasifleştirme metofunu yAZ
         */
    }
}