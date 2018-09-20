package com.overseaslabs.examples.web;

import com.overseaslabs.examples.web.controller.EmailController;
import com.overseaslabs.examples.web.controller.IndexController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexController.class)
@EnableSpringDataWebSupport
class IndexControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void testIndex() throws Exception {
        final String[] routes = {"/", "/users", "/emails", "/info"};
        Arrays.stream(routes).forEach((String route) -> {
            try {
                mvc.perform(MockMvcRequestBuilders.get(route)).andExpect(status().isOk());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
