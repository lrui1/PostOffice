package edu.jmu.lrui1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class PostOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostOfficeApplication.class, args);
    }

}
