package cn.tedu.sp08;
/**
 * Hystrix dashboard 仪表盘
 * hystrix 熔断
整个链路达到一定的阈值，默认情况下，10秒内产生超过20次请求，则符合第一个条件。
满足第一个条件的情况下，如果请求的错误百分比大于阈值，则会打开断路器，默认为50%。
Hystrix的逻辑，先判断是否满足第一个条件，再判断第二个条件，如果两个条件都满足，则会开启断路器

断路器打开 5 秒后，会处于半开状态，会尝试转发请求，如果仍然失败，保持打开状态，如果成功，则关闭断路器

可以使用 apache 的并发访问测试工具 ab:
 在其bin 目录下cmd,
  ab -n 20000 -c 50 http://localhost:3001/item-service/35
  '
  hystrix 配置:
   hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds
	请求超时时间，超时后触发失败降级

	hystrix.command.default.circuitBreaker.requestVolumeThreshold
	10秒内请求数量，默认20，如果没有达到该数量，即使请求全部失败，也不会触发断路器打开

	hystrix.command.default.circuitBreaker.errorThresholdPercentage
  失败请求百分比，达到该比例则触发断路器打开

 hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds
	断路器打开多长时间后，再次允许尝试访问（半开），仍失败则继续保持打开状态，如成功访问则关闭断路器，默认 5000
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
@EnableDiscoveryClient
@EnableHystrixDashboard
@SpringBootApplication
public class Sp07HystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp07HystrixDashboardApplication.class, args);
	}

}
