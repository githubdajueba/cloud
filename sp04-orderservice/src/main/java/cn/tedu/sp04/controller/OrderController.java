package cn.tedu.sp04.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
public class OrderController {
	@Autowired
	private OrderService orderService;
	@RequestMapping("/{orderId}")
	public JsonResult<Order> getOrder(
			@PathVariable
			String orderId){
		Order order = orderService.getOrder(orderId);
		return JsonResult.ok(order);
	}
	@GetMapping("/")
	public JsonResult<?> addOrder(){
		Order order = new Order();
		order.setId("546372");
		order.setUser(new User(8,null,null));
		order.setItems(Arrays.asList(new Item[] {
				new Item(1,"商品 1",null),
				new Item(2,"商品 2",null),
				new Item(3,"商品 3",null),
				new Item(4,"商品 4",null)
		
		}));
		orderService.addOrder(order);
		return JsonResult.ok();
	}
}
