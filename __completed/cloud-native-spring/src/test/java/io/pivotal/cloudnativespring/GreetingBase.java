package io.pivotal.cloudnativespring;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;

public class GreetingBase {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(new CloudNativeSpringApplication());
    }
}