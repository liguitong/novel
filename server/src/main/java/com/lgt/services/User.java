package com.lgt.services;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User implements CommandLineRunner {
    boolean debug = false;
    List<String> files ;
    String name;
    public User(ApplicationArguments args){
        this.debug = args.containsOption("debug");
        this.files = args.getNonOptionArgs();
        this.name = "liguitong";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override

    public void run(String... args) {
        System.out.println(debug);
        System.out.println(files);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
