package cn.tedu.sp02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//@EnableEurekaClient可省
@SpringBootApplication
public class Sp02ItemserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp02ItemserviceApplication.class, args);
	}

}
