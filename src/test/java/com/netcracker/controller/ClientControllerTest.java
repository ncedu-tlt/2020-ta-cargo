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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("IntegrationTest")
@SpringBootTest
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void create() throws Exception {
        String body =
                "{\n" +
                        "    \"lastName\": \"Ivanov\",\n" +
                        "    \"firstName\": \"Ivan\",\n" +
                        "    \"middleName\": \"Ivanovich\",\n" +
                        "    \"phone\": \"111111\",\n" +
                        "    \"email\": \"ivan@mail.ru\",\n" +
                        "    \"driveCategory\": \"B\" " +
                        "}";

        mvc.perform(post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(jsonPath("userId", is(1)))
                .andExpect(jsonPath("lastName", is("Ivanov")))
                .andExpect(jsonPath("firstName", is("Ivan")))
                .andExpect(jsonPath("middleName", is("Ivanovich")))
                .andExpect(jsonPath("phone", is("111111")))
                .andExpect(jsonPath("email", is("ivan@mail.ru")))
                .andExpect(jsonPath("driveCategory", is("B")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void displayAll() throws Exception {
        mvc.perform(get("/client")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].userId", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].lastName", containsInAnyOrder("Golev", "Smirnov")))
                .andExpect(jsonPath("$.[*].firstName", containsInAnyOrder("Kirill", "Andrey")))
                .andExpect(jsonPath("$.[*].middleName", containsInAnyOrder("Aleksandrovich", "Dmitrievich")))
                .andExpect(jsonPath("$.[*].phone", containsInAnyOrder("112233", "223344")))
                .andExpect(jsonPath("$.[*].email", containsInAnyOrder("kirill@mail.ru", "andrey@mail.ru")))
                .andExpect(jsonPath("$.[*].driveCategory", containsInAnyOrder("A", "B")))
                .andExpect(jsonPath("$.[*].password", containsInAnyOrder(emptyOrNullString(), emptyOrNullString())))
                .andExpect(jsonPath("$.[*].role", containsInAnyOrder(emptyOrNullString(), emptyOrNullString())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void displayById() throws Exception {
        mvc.perform(get("/client/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId", is(1)))
                .andExpect(jsonPath("lastName", is("Golev")))
                .andExpect(jsonPath("firstName", is("Kirill")))
                .andExpect(jsonPath("middleName", is("Aleksandrovich")))
                .andExpect(jsonPath("phone", is("112233")))
                .andExpect(jsonPath("email", is("kirill@mail.ru")))
                .andExpect(jsonPath("driveCategory", is("A")))
                .andExpect(jsonPath("password", is(emptyOrNullString())))
                .andExpect(jsonPath("role", is(emptyOrNullString())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void displayByEmail() throws Exception {
        mvc.perform(get("/client/email/{email}", "kirill@mail.ru")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId", is(1)))
                .andExpect(jsonPath("lastName", is("Golev")))
                .andExpect(jsonPath("firstName", is("Kirill")))
                .andExpect(jsonPath("middleName", is("Aleksandrovich")))
                .andExpect(jsonPath("phone", is("112233")))
                .andExpect(jsonPath("email", is("kirill@mail.ru")))
                .andExpect(jsonPath("driveCategory", is("A")))
                .andExpect(jsonPath("password", is(emptyOrNullString())))
                .andExpect(jsonPath("role", is(emptyOrNullString())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void displayByPhone() throws Exception {
        mvc.perform(get("/client/phone/{phone}", "112233")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("userId", is(1)))
                .andExpect(jsonPath("lastName", is("Golev")))
                .andExpect(jsonPath("firstName", is("Kirill")))
                .andExpect(jsonPath("middleName", is("Aleksandrovich")))
                .andExpect(jsonPath("phone", is("112233")))
                .andExpect(jsonPath("email", is("kirill@mail.ru")))
                .andExpect(jsonPath("driveCategory", is("A")))
                .andExpect(jsonPath("password", is(emptyOrNullString())))
                .andExpect(jsonPath("role", is(emptyOrNullString())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/client/{id}", 2))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteNotExistingObject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/client/{id}", 10))
                .andExpect(status().isNotModified())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void modify() throws Exception {

        String body =
                "{\n" +
                        "    \"userId\": 1,\n" +
                        "    \"firstName\": \"Danil\",\n" +
                        "    \"middleName\": \"Danilovich\" " +
                        "}";


        mvc.perform(patch("/client")
                .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(jsonPath("userId", is(1)))
                .andExpect(jsonPath("lastName", is("Golev")))
                .andExpect(jsonPath("firstName", is("Danil")))
                .andExpect(jsonPath("middleName", is("Danilovich")))
                .andExpect(jsonPath("phone", is("112233")))
                .andExpect(jsonPath("email", is("kirill@mail.ru")))
                .andExpect(jsonPath("driveCategory", is("A")))
                .andExpect(jsonPath("password", is("11")))
                .andExpect(jsonPath("role", is("USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:client.sql")
    public void modifyNotExistingObject() throws Exception {

        String body =
                "{\n" +
                        "    \"userId\": 5,\n" +
                        "    \"firstName\": \"Danil\",\n" +
                        "    \"middleName\": \"Danilovich\" " +
                        "}";

        mvc.perform(patch("/client")
                .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isNotModified())
                .andDo(print());
    }
 }

