package com.sdut.laboratorybackmodule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sdut.laboratorybackmodule.mapper")
public class LaboratoryBackModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoryBackModuleApplication.class, args);
    }

}
