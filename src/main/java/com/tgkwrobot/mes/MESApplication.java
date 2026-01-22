package com.tgkwrobot.mes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.tgkwrobot.mes.mapper")
public class MESApplication {

    public static void main(String[] args) {
        SpringApplication.run(MESApplication.class, args);
    }

}
