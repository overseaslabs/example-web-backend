package com.overseaslabs.examples.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@Controller
public class IndexController {

    @GetMapping("/{template:\\w+}")
    public String index(@PathVariable Optional<String> template) {
        return "index";
    }
}
