package com.jessica.shirotest;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(Application.class);
    }
}
