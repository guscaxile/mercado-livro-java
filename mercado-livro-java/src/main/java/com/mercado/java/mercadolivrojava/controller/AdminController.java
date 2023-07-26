package com.mercado.java.mercadolivrojava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping("/reports")
    public String report() {
        return "This is a report. Only admin can see it!";
    }
}
