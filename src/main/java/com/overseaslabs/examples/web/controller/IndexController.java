package com.overseaslabs.examples.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    /**
     * Returns the index page
     */
    @GetMapping({"/", "/users", "/emails", "/info"})
    @CrossOrigin(origins = "*")
    public String index() {
        return "index";
    }
}
