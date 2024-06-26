package com.example.calendar_backend.ejb;
import javax.ejb.Stateless;

@Stateless
public class test {

    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}