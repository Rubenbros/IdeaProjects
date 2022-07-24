package rubenbros.publicService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PublicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicServiceApplication.class, args);
	}

}
