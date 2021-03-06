package com.hq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author mall
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HQApplication {
    public static void main(String[] args) {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(HQApplication.class, args);
        System.out.println("start success!");
    }
}