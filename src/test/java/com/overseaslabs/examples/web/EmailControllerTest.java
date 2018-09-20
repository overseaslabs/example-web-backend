package com.overseaslabs.examples.web;

import com.overseaslabs.examples.web.controller.EmailController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmailController.class)
@EnableSpringDataWebSupport
class EmailControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    NetObjectsFactory netObjectsFactory;

    @Test
    void testGetEmails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/mailer/emails").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
