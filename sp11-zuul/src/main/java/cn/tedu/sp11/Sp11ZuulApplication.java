package cn.tedu.sp11;
/**
 * zuul + ribbon 负载均衡
	zuul 已经集成了 ribbon，默认已经实现了负载均衡

	zuul + ribbon 重试
	pom.xml 需要添加 spring-retry 依赖
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
@EnableDiscoveryClient
@EnableZuulProxy
@SpringBootApplication
public class Sp11ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp11ZuulApplication.class, args);
	}

}
