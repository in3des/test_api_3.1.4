package api.test.test_api_314;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//first request
	RestTemplate template = new RestTemplate();
	ResponseEntity<String> forEntity = template.getForEntity("http://google.bg", String.class);
forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);

}
