package g3.rqm.requestmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RequestManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestManagerApplication.class, args);
	}

}
