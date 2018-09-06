package com.overseaslabs.examples.web.controller;

import com.overseaslabs.examples.mailer.entity.Email;
import com.overseaslabs.examples.utils.RestPageImpl;
import com.overseaslabs.examples.web.NetObjectsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
    ResponseEntity<RestPageImpl<Email>> get(Pageable pageable) {
        ParameterizedTypeReference<RestPageImpl<Email>> ptr = new ParameterizedTypeReference<RestPageImpl<Email>>() {
        };
        return restTemplate.exchange(netObjectsFactory.makeUri("emails", pageable), HttpMethod.GET, netObjectsFactory.makeHttpEntity("mailer"), ptr);
    }
}
