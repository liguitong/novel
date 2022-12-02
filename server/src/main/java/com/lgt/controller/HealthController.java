package com.lgt.controller;

import com.lgt.beans.Novel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(value="/")
public class HealthController {
    @GetMapping("/health")
    public String health( ) {
        return "Normal status.";
    }
}
