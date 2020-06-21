package cn.tedu.sp06;
/**
 * ribbon 提供了负载均衡和重试功能, 它底层是使用 RestTemplate 进行 Rest api 调用
 * RestTemplate 是StringBoot提供的一个Rest远程调用工具
 * eureka-client 中已经包含 ribbon 依赖
 * RestTemplate 是用来调用其他微服务的工具类，封装了远程调用代码，提供了一组用于远程调用的模板方法，例如：getForObject()、postForObject() 等
 * eureka 依赖中已经包含了 ribbon
 * 重试:添加 spring-retry 依赖
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
//@EnableDiscoveryClient // eureka discovery client
@SpringBootApplication
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
