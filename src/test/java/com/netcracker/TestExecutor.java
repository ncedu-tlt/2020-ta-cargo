package com.netcracker;


import com.netcracker.controller.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressControllerTest.class,
        BoxControllerTest.class,
        ClientControllerTest.class,
        TrailerControllerTest.class,
        OrderControllerTest.class,
        CarControllerTest.class
})
public class TestExecutor {
}
