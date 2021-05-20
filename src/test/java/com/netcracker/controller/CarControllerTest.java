package com.netcracker.controller;



import com.netcracker.model.Car;
import com.netcracker.service.CarService;

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


import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("IntegrationTest")
@SpringBootTest
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
     CarService carService;


    @Test
    public void create() throws Exception {
        String body =
                "{\n" +
                        "    \"name\": \"KIA\",\n" +
                        "    \"volume\": \"666\",\n" +
                        "    \"liftingCapacity\": \"666\",\n" +
                        "    \"Client\": {\"userId\": \"3\"} " +
                        "}";


        mvc.perform(post("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(jsonPath("name", is("KIA")))
                .andExpect(jsonPath("volume", is(666.0)))
                .andExpect(jsonPath("liftingCapacity", is(666)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:car.sql")
    public void displayAll() throws Exception {
        mvc.perform(get("/car")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("tesla", "mazda")))
                .andExpect(jsonPath("$.[*].volume", containsInAnyOrder(2.5, 6.69)))
                .andExpect(jsonPath("$.[*].liftingCapacity", containsInAnyOrder(256, 69)))
                .andExpect(status().isOk())
                .andDo(print());

    }


    @Test
    @Sql(scripts = "classpath:car.sql")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/car/{id}", 2))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteNotExistingObject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/car/{id}", 10))
                .andExpect(status().isNotModified())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:car.sql")
    public void modify() throws Exception {


        String body =
                "{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"BUGATTY\",\n" +
                        "    \"liftingCapacity\": 666.66" +
                        "}";


        mvc.perform(patch("/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andDo(print());

        Car car = carService.displayById(1);
        assertEquals(car.getName(),"BUGATTY");

    }

    @Test
    @Sql(scripts = "classpath:car.sql")
    public void displayByEmail() throws Exception {
        mvc.perform(get("/car/byOrderId/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("tesla")))
                .andExpect(jsonPath("volume", is(2.5)))
                .andExpect(jsonPath("liftingCapacity", is(256)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
