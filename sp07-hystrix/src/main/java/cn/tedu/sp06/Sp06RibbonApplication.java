package cn.tedu.sp06;

/**
 * @EnableCircuitBreaker 启用 hystrix 断路器
 */

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

//@EnableDiscoveryClient // eureka discovery client
//@SpringBootApplication
//@EnableCircuitBreaker

@SpringCloudApplication // 注解代替三个注解
public class Sp06RibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}

	//创建 RestTemplate 实例，并存入 spring 容器
	/**
	 * @LoadBalanced 负载均衡注解，会对 RestTemplate 实例进行封装，创建动态代理对象，并切入（AOP）负载均衡代码，把请求分发到集群中的服务器


	 * @return
	 * 
	 * 重试:
	 *  OkToRetryOnAllOperations=true
	默认只对GET请求重试, 当设置为true时, 对POST等所有类型请求都重试
	MaxAutoRetriesNextServer
	更换实例的次数
	MaxAutoRetries
	当前实例重试次数，尝试失败会更换下一个实例
	主程序设置 RestTemplate 的请求工厂的超时属性
	
	//RestTemplate 中默认的 Factory 实例中，两个超时属性默认是 -1，
		//未启用超时，也不会触发重试
		//return new RestTemplate();
	 */
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setConnectTimeout(1000);
		f.setReadTimeout(1000);
		return new RestTemplate();
	}
}
