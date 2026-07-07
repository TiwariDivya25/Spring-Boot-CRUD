package dev.tiwaridivya.springbootcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringbootcrudApplication {

	public static void main(String[] args) {

        SpringApplication.run(SpringbootcrudApplication.class, args);

        System.out.print("hello world");
	}

}
