package cn.tedu.sp06.controller;
/**
 * 为每个方法添加降级方法，例如 getItems() 添加降级方法 getItemsFB()
 * 添加 @HystrixCommand 注解，指定降级方法名
 * hystrix 超时设置
 hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds

 hystrix等待超时后, 会执行降级代码, 快速向客户端返回降级结果, 默认超时时间是1000毫秒


 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;

@RestController
public class RibbonController {

	@Autowired
	private RestTemplate rt;
	@HystrixCommand(fallbackMethod = "getItemsFB") 
	@GetMapping("/item-service/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
		//向指定微服务地址发送 get 请求，并获得该服务的返回结果 
		//{1} 占位符，用 orderId 填充
		// 访问路径设置为服务id
		return rt.getForObject("http://item-service/{1}", JsonResult.class, orderId);
		/*
		 * return rt.getForObject("http://localhost:8001/{1}", JsonResult.class,
		 * orderId);
		 */	}

	@HystrixCommand(fallbackMethod = "decreaseNumberFB")
	@PostMapping("/item-service/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		//发送 post 请求
		return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
	}

	/////////////////////////////////////////

	@HystrixCommand(fallbackMethod = "getUserFB")
	@GetMapping("/user-service/{userId}")
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
	}

	@GetMapping("/user-service/{userId}/score") 
	@HystrixCommand(fallbackMethod = "addScoreFB")
	public JsonResult addScore(
			@PathVariable Integer userId, Integer score) {
		return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
	}

	/////////////////////////////////////////
	@HystrixCommand(fallbackMethod = "getOrderFB")
	@GetMapping("/order-service/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
	}
	@HystrixCommand(fallbackMethod = "addOrderFB")
	@GetMapping("/order-service")
	public JsonResult addOrder() {
		return rt.getForObject("http://order-service/", JsonResult.class);
	}
	/////////////////////////////////////////

	//降级方法的参数和返回值，需要和原始方法一致，方法名任意
	public JsonResult<List<Item>> getItemsFB(String orderId) {
		return JsonResult.err("获取订单商品列表失败");
	}
	public JsonResult decreaseNumberFB(List<Item> items) {
		return JsonResult.err("更新商品库存失败");
	}
	public JsonResult<User> getUserFB(Integer userId) {
		return JsonResult.err("获取用户信息失败");
	}
	public JsonResult addScoreFB(Integer userId, Integer score) {
		return JsonResult.err("增加用户积分失败");
	}
	public JsonResult<Order> getOrderFB(String orderId) {
		return JsonResult.err("获取订单失败");
	}
	public JsonResult addOrderFB() {
		return JsonResult.err("添加订单失败");
	}

}
