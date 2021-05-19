package com.netcracker.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("IntegrationTest")
@SpringBootTest
public class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void create() throws Exception {
        String body =
                "{\n" +
                        "    \"country\": \"RF\",\n" +
                        "    \"city\": \"Moscow\",\n" +
                        "    \"street\": \"Jukova\",\n" +
                        "    \"home\": \"82\",\n" +
                        "    \"apartment\": \"573\" " +
                        "}";


        mvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(jsonPath("addressId", is(1)))
                .andExpect(jsonPath("country", is("RF")))
                .andExpect(jsonPath("city", is("Moscow")))
                .andExpect(jsonPath("street", is("Jukova")))
                .andExpect(jsonPath("home", is("82")))
                .andExpect(jsonPath("apartment", is("573")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:address.sql")
    public void displayAll() throws Exception {
        mvc.perform(get("/address")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].addressId", containsInAnyOrder(3, 2)))
                .andExpect(jsonPath("$.[*].country", containsInAnyOrder("RF", "RF")))
                .andExpect(jsonPath("$.[*].city", containsInAnyOrder("Tolyatti", "Samara")))
                .andExpect(jsonPath("$.[*].street", containsInAnyOrder("Sverdlova", "Lenina")))
                .andExpect(jsonPath("$.[*].home", containsInAnyOrder("11", "33")))
                .andExpect(jsonPath("$.[*].apartment", containsInAnyOrder("22", "555")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:address.sql")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/address/{id}", 3  ))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteNotExistingObject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/address/{id}", 10))
                .andExpect(status().isNotModified())
                .andDo(print());
    }
}
