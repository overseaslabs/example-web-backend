package com.overseaslabs.examples.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NetObjectsFactoryTest {
    @Autowired
    NetObjectsFactory factory;

    @Test
    void testMakeHttpEntity() {
        HttpEntity e = factory.makeHttpEntity("foo");
        assertEquals("foo", e.getHeaders().getHost().getHostName());
    }

    @Test
    void testMakeHttpEntityWithBody() {
        HttpEntity<String> e = factory.makeHttpEntity("foo", "bar");
        assertEquals("foo", e.getHeaders().getHost().getHostName());
        assertEquals("bar", e.getBody());
    }

    @Test
    void testMakeUri() {
        URI uri = factory.makeUri("foo");
        assertEquals("http://kong:8000/foo", uri.toString());
    }

    @Test
    void testMakePageableUri() {
        URI uri = factory.makeUri("foo", PageRequest.of(1, 1));
        assertEquals("http://kong:8000/foo?page=1&size=1&sort=id,desc", uri.toString());
    }
}
