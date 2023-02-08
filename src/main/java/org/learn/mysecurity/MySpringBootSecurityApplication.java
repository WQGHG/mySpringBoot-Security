package org.learn.mysecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.wqg.base.until.SpringContextUtil;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"org.learn.mysecurity", "org.wqg.base"})
@MapperScan("org.learn.mysecurity.mapper")
public class MySpringBootSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }
}
