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

import java.net.URI;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NetObjectsFactory netObjectsFactory;

    @GetMapping("/users")
    ResponseEntity<User> get(Pageable pageable) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users", pageable), HttpMethod.GET, netObjectsFactory.makeHttpEntity("ureg"), User.class);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users/" + id), HttpMethod.DELETE, netObjectsFactory.makeHttpEntity("ureg"), String.class);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        return restTemplate.exchange(netObjectsFactory.makeUri("users/" + id), HttpMethod.PUT, netObjectsFactory.makeHttpEntity("ureg", user), User.class);
    }
}
