package com.netcracker.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("IntegrationTest")
@SpringBootTest
public class TrailerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Sql(scripts = "classpath:trailer.sql")
    public void create() throws Exception {
        String body =
                "{\n" +
                        "    \"id\" : 1,\n" +
                        "    \"name\" : \"buk\",\n" +
                        "    \"number\" : \"10\",\n" +
                        "    \"volume\" : 1.3,\n" +
                        "    \"liftingCapacity\" : 13,\n" +
                        "    \"car\" : {" +
                        "    \"id\": 1\n" +
                        "}\n" +
                        "}\n";

        mvc.perform(post("/trailer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("buk")))
                .andExpect(jsonPath("number", is("10")))
                .andExpect(jsonPath("volume", is(1.3)))
                .andExpect(jsonPath("liftingCapacity", is(13)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:trailer_with_trailer.sql")
    public void displayAll() throws Exception {
        mvc.perform(get("/trailer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:trailer_with_trailer.sql")
    public void displayById() throws Exception {
        mvc.perform(get("/trailer/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:trailer_with_trailer.sql")
    public void displayByVolume() throws Exception {
        mvc.perform(get("/trailer/volume/1.1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:trailer_with_trailer.sql")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/trailer/{id}", 1))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:trailer_with_trailer.sql")
    public void modify() throws Exception {
        String body =
                "{\n" +
                        "    \"id\" : 1,\n" +
                        "    \"name\" : \"kub\",\n" +
                        "    \"number\" : \"101\",\n" +
                        "    \"volume\" : 11.3,\n" +
                        "    \"liftingCapacity\" : 113\n" +
                        "}\n";

        mvc.perform(patch("/trailer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andDo(print());

        mvc.perform(get("/trailer/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("kub")))
                .andExpect(jsonPath("number", is("101")))
                .andExpect(jsonPath("volume", is(11.3)))
                .andExpect(jsonPath("liftingCapacity", is(113)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
