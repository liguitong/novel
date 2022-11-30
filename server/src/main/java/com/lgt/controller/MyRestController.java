package com.lgt.controller;

import com.lgt.services.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(value="/users")
public class MyRestController {
    @Autowired
    User user;
    @RequestMapping(value="/a", method = RequestMethod.GET)
    public User getUser(){
        return user;
    }
}
