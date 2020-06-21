package cn.tedu.sp10;
/**
 * hystrix + turbine 集群聚合监控

	turbine 监控路径
	http://localhost:5001/turbine.stream
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
@EnableDiscoveryClient
@EnableTurbine
@SpringBootApplication
public class Sp10TurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp10TurbineApplication.class, args);
	}

}
