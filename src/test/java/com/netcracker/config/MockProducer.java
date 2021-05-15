package com.netcracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Configuration
@SpringBootTest(webEnvironment = RANDOM_PORT)
@WebAppConfiguration
@Profile("IntegrationTest")
@ContextConfiguration(classes = {EmbeddedPostgresConfiguration.class})
public class MockProducer {

    @Autowired
    private WebApplicationContext applicationContext;

    @Bean
    public MockMvc mockMvc(){
        return MockMvcBuilders.webAppContextSetup(applicationContext).alwaysDo(print()).build();
    }
}
