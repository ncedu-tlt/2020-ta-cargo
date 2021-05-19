package com.netcracker;


import com.netcracker.controller.AddressControllerTest;
import com.netcracker.controller.BoxControllerTest;
import com.netcracker.controller.ClientControllerTest;
import com.netcracker.controller.TrailerControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressControllerTest.class,
        BoxControllerTest.class,
        ClientControllerTest.class,
        TrailerControllerTest.class
})
public class TestExecutor {
}
