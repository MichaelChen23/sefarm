package com.sefarm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ServiceApplication启动类
 *
 * @author mc
 * @date 2018-3-18
 */
@SpringBootApplication
@ImportResource({"classpath:dubbo-provider.xml"})
@EnableTransactionManagement
@ComponentScan(basePackages={"com.sefarm.service"})
@MapperScan(basePackages = "com.sefarm.dao")
public class SeFarmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeFarmServiceApplication.class, args);
    }

}
