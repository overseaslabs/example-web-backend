package com.overseaslabs.examples.web.controller;

import com.overseaslabs.examples.ureg.entity.User;
import com.overseaslabs.examples.utils.RestPageImpl;
import com.overseaslabs.examples.web.NetObjectsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NetObjectsFactory netObjectsFactory;

    @GetMapping("/ureg/users")
    ResponseEntity<String> get(Pageable pageable) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users", pageable), HttpMethod.GET, netObjectsFactory.makeHttpEntity("ureg"), String.class);
    }

    @DeleteMapping("/ureg/users/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users/" + id), HttpMethod.DELETE, netObjectsFactory.makeHttpEntity("ureg"), Void.class);
    }

    @PutMapping("/ureg/users/{id}")
    ResponseEntity<String> update(@PathVariable Integer id, @RequestBody User user) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users/" + id), HttpMethod.PUT, netObjectsFactory.makeHttpEntity("ureg", user), String.class);
    }

    @PostMapping("/ureg/users")
    ResponseEntity<String> create(@RequestBody User user) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users"), HttpMethod.POST, netObjectsFactory.makeHttpEntity("ureg", user), String.class);
    }
}
