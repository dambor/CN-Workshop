package io.pivotal.cloudnativespringui;

import io.pivotal.cloudnativespring.domain.City;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CloudNativeSpringUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudNativeSpringUiApplication.class, args);
	}

	@FeignClient(name = "cloud-native-spring", fallback = CityClientFallback.class)
	public interface CityClient {
		@GetMapping(value = "/cities", consumes = "application/hal+json")
		Resources<City> getCities();
	}

	@Configuration
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().permitAll();
		}

	}

	@Component
	public class CityClientFallback implements CityClient {
		@Override
		public Resources<City> getCities() {
			// We'll just return an empty response
			return new Resources<City>(Collections.emptyList());
		}
	}

	@Component
	public class GreetingService {

		public String greeting(String name) {
			ResponseEntity<String> response = new RestTemplate().getForEntity(
					"http://localhost:8888/greeting/" + name, String.class);
			return response.getBody();
		}
	}

}
