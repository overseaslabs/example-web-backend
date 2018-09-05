package com.overseaslabs.examples.web.controller;

import com.overseaslabs.examples.mailer.entity.Email;
import com.overseaslabs.examples.web.NetObjectsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmailController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NetObjectsFactory netObjectsFactory;

    @GetMapping("/mailer/emails")
    ResponseEntity<Email> get(Pageable pageable) {
        return restTemplate.exchange(netObjectsFactory.makeUri("emails", pageable), HttpMethod.GET, netObjectsFactory.makeHttpEntity("mailer"), Email.class);
    }
}
