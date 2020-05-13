package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class Test {

    private final Environment env;

    //@PostConstruct
    public void init(){
        System.out.println("init");
    }
    public void hello(){
        System.out.println("dfsafdsfds");
    }
}
