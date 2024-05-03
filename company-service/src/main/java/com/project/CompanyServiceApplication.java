package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompanyServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(CompanyServiceApplication.class, args);

        /**
         * Site yöneticisinin şirket başvurularını görüntüleyebilmesi ve onaylama/reddetme işlemlerini
         * gerçekleştirebilmesi için arayüz tasarımının ve işlevselliğinin sağlanması.
         * (authların statei pending olanları görüntü methodu olacak.
         * Onaylama mail ile olacak, butona tıklanınca o onay sayfasına gitsin.
         * Reddedince reddedilid diye mail gitsin.)
         */

        /**
         * managaer teklif gönderince company de oluşsun create rabbit ile haberleştir.
         * company id ile state pendingleri getir
         */
    }
}