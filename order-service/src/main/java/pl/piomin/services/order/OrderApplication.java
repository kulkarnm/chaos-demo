package pl.piomin.services.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path","/order-service");
		SpringApplication.run(OrderApplication.class, args);
	}
	
}
