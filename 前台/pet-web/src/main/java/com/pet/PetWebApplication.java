package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.pet.mapper")
public class PetWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetWebApplication.class, args);
    }

}
