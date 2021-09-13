package com.okt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create by obeeey on 2021/3/10
 * 即使再小的帆也能远航
 */
@SpringBootApplication
@MapperScan("com.okt.dao")
public class GpApplication {
    public static void main(String[] args) {
        SpringApplication.run(GpApplication.class, args);
    }
}

