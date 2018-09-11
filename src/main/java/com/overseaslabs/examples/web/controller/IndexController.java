package com.overseaslabs.examples.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/users", "/emails", "/info"})
    public String index() {
        return "index";
    }
}
