package com.sefarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * WebApplication启动类
 *
 * @author mc
 * @date 2018-3-18
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.sefarm.controller"})
public class SeFarmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeFarmWebApplication.class, args);
    }
}
