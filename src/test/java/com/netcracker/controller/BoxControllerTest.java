package com.netcracker.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("IntegrationTest")
@SpringBootTest
public class BoxControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Sql(scripts = "classpath:box.sql")
    void modify() throws Exception {

        String body =
                "{\n" +
                        "    \"boxId\" : 1,\n" +
                        "    \"name\" : \"box2\",\n" +
                        "    \"height\" : 15\n" +
                        "}";

        mvc.perform(patch("/box")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andDo(print());

        mvc.perform(get("/box/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("boxId", is(1)))
                .andExpect(jsonPath("name", is("box2")))
                .andExpect(jsonPath("height", is(15)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:box.sql")
    void create() throws Exception {
        String body =
                "{\n" +
                        "    \"boxId\" : 1,\n" +
                        "    \"name\" : \"box\",\n" +
                        "    \"height\" : 10,\n" +
                        "    \"width\" : 11,\n" +
                        "    \"volume\" : 12.2,\n" +
                        "    \"weight\" : 13.3,\n" +
                        "    \"client\" :  {\"userId\" : 1},\n" +
                        "    \"typeCargo\" : {\"typeId\" : 2}\n" +
                        "}";

        mvc.perform(post("/box")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(jsonPath("boxId", is(1)))
                .andExpect(jsonPath("name", is("box")))
                .andExpect(jsonPath("height", is(10)))
                .andExpect(jsonPath("width", is(11)))
                .andExpect(jsonPath("volume", is(12.2)))
                .andExpect(jsonPath("$.client.userId", is(1)))
                .andExpect(jsonPath("$.typeCargo.typeId", is(2)))
                .andExpect(jsonPath("weight", is(13.3)))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Sql(scripts = "classpath:box.sql")
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/box/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:box.sql")
    void showAll() throws Exception {
        mvc.perform(get("/box")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:box.sql")
    void showById() throws Exception {
        mvc.perform(get("/box/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("boxId", is(2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:box.sql")
    void displayByClientId() throws Exception {
        mvc.perform(get("/box/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("boxId", is(1)))
                .andExpect(jsonPath("name", is("box")))
                .andExpect(jsonPath("height", is(10)))
                .andExpect(jsonPath("width", is(11)))
                .andExpect(jsonPath("volume", is(12.2)))
                .andExpect(jsonPath("$.client.userId", is(1)))
                .andExpect(jsonPath("$.typeCargo.typeId", is(2)))
                .andExpect(jsonPath("weight", is(13.3)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
