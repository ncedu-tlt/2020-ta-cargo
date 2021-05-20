package com.netcracker.controller;

import com.netcracker.model.Order;
import com.netcracker.service.OrderService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ActiveProfiles("IntegrationTest")
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    OrderService orderService;


    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showAll() throws Exception{

        mvc.perform(get("/order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(4)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(4, 5, 6, 7)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order", "order", "order", "order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(1, 2, 2, 2)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(2, 3, 3, 3)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1, 1, 1, 1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(1, 1, 2, 2)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800, 3800, 3800, 3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(2, 2, 3, 3)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1, 2, 1, 2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showAllByBoxClientIdAndStatus() throws Exception{

        mvc.perform(get("/order/boxClientIdAndStatus/{id}/{status}", 2, "open")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(1)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(6)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(2)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(3)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(2)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(3)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showAllByBoxClientIdAndNotStatus() throws Exception{

        mvc.perform(get("/order/boxClientIdAndNotStatus/{id}/{status}", 2, "open")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(1)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(7)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(2)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(3)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(2)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(3)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showAllByDriverIdAndStatus() throws Exception{

        mvc.perform(get("/order/driverIdAndStatus/{id}/{status}",
                 1, "open")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(4, 6)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order", "order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(2, 3)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1, 1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800, 3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(2, 3)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1, 1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showByLocationAndDestinationAndTypeAndPrice() throws Exception{

            mvc.perform(get("/order/byLocDestTypePrice/{locCity}/{destCity}/{typeId}/{price}/{status}",
                    "Samara", "Tolyatti", 1, 5000, "open")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[*]", iterableWithSize(1)))
                    .andExpect(jsonPath("$.[*].id", containsInAnyOrder( 6)))
                    .andExpect(jsonPath("$.[*].name", containsInAnyOrder(  "order")))
                    .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(2)))
                    .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(3)))
                    .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1)))
                    .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(2)))
                    .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800)))
                    .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(3)))
                    .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1)))
                    .andExpect(status().isOk())
                    .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showAllByBoxClientId() throws Exception{

        mvc.perform(get("/order/boxClientId/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(6, 7)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order", "order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(2, 2)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(3, 3)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1, 1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(2, 2)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800, 3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(3, 3)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1, 2)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void showAllByStatusName() throws Exception{

        mvc.perform(get("/order/status/{name}", "open")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(2)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(4, 6)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order", "order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(3, 2)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1, 1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800, 3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(2, 3)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1, 1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void create() throws Exception {


        String body =
                "{\n" +
                        "    \"name\": \"order\",\n" +
                        "    \"destination\": {\"addressId\": 2},\n" +
                        "    \"location\": {\"addressId\": 1},\n" +
                        "    \"driver\": {\"userId\": 1},\n" +
                        "    \"box\": {\"boxId\": 1},\n" +
                        "    \"price\": \"3800\",\n" +
                        "    \"receiver\": {\"userId\": 2},\n" +
                        "    \"status\": {\"id\": 1}" +
                        "}";


        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("order")))
                .andExpect(jsonPath("destination.addressId", is(2)))
                .andExpect(jsonPath("location.addressId", is(1)))
                .andExpect(jsonPath("driver.userId", is(1)))
                .andExpect(jsonPath("box.boxId", is(1)))
                .andExpect(jsonPath("price", is(3800)))
                .andExpect(jsonPath("receiver.userId", is(2)))
                .andExpect(jsonPath("status.id", is(1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/order/{id}", 4))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteNotExistingObject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/order/{id}", 10))
                .andExpect(status().isNotModified())
                .andDo(print());
    }


    @Test
    @Sql(scripts = "classpath:order.sql")
    public void searchByCity() throws Exception{

        mvc.perform(get("/order/showCity/{city}", "Tolyatti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", iterableWithSize(1)))
                .andExpect(jsonPath("$.[*].id", containsInAnyOrder(4)))
                .andExpect(jsonPath("$.[*].name", containsInAnyOrder("order")))
                .andExpect(jsonPath("$.[*].destination.addressId", containsInAnyOrder(1)))
                .andExpect(jsonPath("$.[*].location.addressId", containsInAnyOrder(2)))
                .andExpect(jsonPath("$.[*].driver.userId", containsInAnyOrder(1)))
                .andExpect(jsonPath("$.[*].box.boxId", containsInAnyOrder(1)))
                .andExpect(jsonPath("$.[*].price", containsInAnyOrder(3800)))
                .andExpect(jsonPath("$.[*].receiver.userId", containsInAnyOrder(2)))
                .andExpect(jsonPath("$.[*].status.id", containsInAnyOrder(1)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = "classpath:order.sql")
    public void modify() throws Exception {

        String body =
                "{\n" +
                        "    \"id\": 4,\n" +
                        "    \"name\": \"carGo\" "+
                        "}";

        mvc.perform(patch("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andDo(print());

        Order order = orderService.displayById(4);
        assertEquals(order.getName(),"carGo");
    }


    @Test
    @Sql(scripts = "classpath:order.sql")
    public void displayById() throws Exception{

        mvc.perform(get("/order/displayById/{$id}", 4)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(4)))
                .andExpect(jsonPath("name", is("order")))
                .andExpect(jsonPath("destination.addressId", is(1)))
                .andExpect(jsonPath("location.addressId", is(2)))
                .andExpect(jsonPath("driver.userId", is(1)))
                .andExpect(jsonPath("box.boxId", is(1)))
                .andExpect(jsonPath("price", is(3800)))
                .andExpect(jsonPath("receiver.userId", is(2)))
                .andExpect(jsonPath("status.id", is(1)))
                .andExpect(status().isOk())
                .andDo(print());


    }
}
