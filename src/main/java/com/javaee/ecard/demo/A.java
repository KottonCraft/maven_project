package com.javaee.ecard.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class A {
    @Autowired
    private B b;
    public String callA(){
        return "callA() -> " + b.callB();

    }
}
