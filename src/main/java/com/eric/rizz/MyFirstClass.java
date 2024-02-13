package com.eric.rizz;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;


public class MyFirstClass {

    private String myVar;

    public MyFirstClass(String myVar) {
        this.myVar = myVar;
    }


    public String getMyVar(String myVar) {
        return this.myVar = myVar;
    }

    public String sayHello() {
        return "Hello from the MyFirstClass ==> myVar = " + myVar;
    }



}
