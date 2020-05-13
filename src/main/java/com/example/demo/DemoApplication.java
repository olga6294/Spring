package com.example.demo;

import com.example.demo.localdirectory.DirectoryListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(DemoApplication.class);
        DirectoryListener directoryListener = applicationContext.getBean(DirectoryListener.class);
        directoryListener.listen();

    }

}
