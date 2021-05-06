package com.netcracker.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("IntegrationTest")
@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void create() {
    }

    @Test
    @Sql(scripts = "classpath:address.sql")
    void displayAll() throws Exception {
        mvc.perform(get("/address")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].addressId", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].country", containsInAnyOrder("RF", "RF")))
                .andExpect(jsonPath("$.[*].city", containsInAnyOrder("Tolyatti", "Samara")))
                .andExpect(jsonPath("$.[*].street", containsInAnyOrder("Sverdlova", "Lenina")))
                .andExpect(jsonPath("$.[*].home", containsInAnyOrder("11", "33")))
                .andExpect(jsonPath("$.[*].apartment", containsInAnyOrder("22", "555")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void delete() {
    }
}
