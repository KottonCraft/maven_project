package com.javaee.ecard.controller;

import com.javaee.ecard.config.ECardGlobalConfig;
import com.javaee.ecard.demo.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class AController {
    @Autowired
    private A a;
    @Autowired
    private ECardGlobalConfig eCardGlobalConfig;

//    @Value("${demo.algo.a}")
//    private String aItem;
//    @Value("${demo.algo.b}")
//    private String bItem;

    @RequestMapping("/a")
    public String returnAvalue(){
        return "http call -> AController() -> " + a.callA();
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "myname", required = false) String name, String p){
        return "hello " + name + p;
    }

    @RequestMapping("/algo/{a}/{b}")
    public StringBuffer algo(@PathVariable("a") int a, @PathVariable("b") int b){
        System.out.println("2. AController.algo() is called.");
        if(b == 0)
            throw new RuntimeException("parameter2 must be not zero!");
//            return eCardGlobalConfig.getAItem()+"="+a;
        else
//            return eCardGlobalConfig.getAItem()+"="+a+","+eCardGlobalConfig.getBItem()+"="+b+" =>result=" + (a + b);
             return new StringBuffer(a+"+"+b
                     +"=" + (a + b));

    }
}
