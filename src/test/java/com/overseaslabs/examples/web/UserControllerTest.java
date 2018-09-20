package com.overseaslabs.examples.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.overseaslabs.examples.ureg.entity.User;
import com.overseaslabs.examples.web.controller.UserController;
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
@WebMvcTest(UserController.class)
@EnableSpringDataWebSupport
class UserControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    RestTemplate restTemplate;

    @MockBean
    NetObjectsFactory netObjectsFactory;

    @Autowired
    ObjectMapper om;

    @Test
    void testGetUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/ureg/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/ureg/users/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        User u = new User();

        u.setEmail("foo@bar.com")
                .setFirstName("foo")
                .setLastName("bar")
                .setId(1);

        mvc.perform(
                MockMvcRequestBuilders
                        .put("/ureg/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(u))
        ).andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        User u = new User();

        u.setEmail("foo@bar.com")
                .setFirstName("foo")
                .setLastName("bar")
                .setId(1);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/ureg/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(u))
        ).andExpect(status().isOk());
    }
}
