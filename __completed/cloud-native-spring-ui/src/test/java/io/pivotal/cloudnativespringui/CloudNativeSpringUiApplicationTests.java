package io.pivotal.cloudnativespringui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
		ids = { "io.pivotal:cloud-native-spring:+:stubs:8888" },
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CloudNativeSpringUiApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private CloudNativeSpringUiApplication.GreetingService greeter;

	@Test
	public void testGreeting() {
		// given:
		String name = "Pivotal";

		// when:
		String result = greeter.greeting(name);

		// then:
		Assert.isTrue(result.endsWith(name), "Greeting was not correct");
	}

}
