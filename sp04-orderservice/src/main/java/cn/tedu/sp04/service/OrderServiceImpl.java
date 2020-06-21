package cn.tedu.sp04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp04.feignclient.ItemFeignClient;
import cn.tedu.sp04.feignclient.UserFeignClient;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ItemFeignClient itemFeign;
	@Autowired
	private UserFeignClient userFeign;
	/**
	 * order--03-user--
	 *      --02-list<item>--
	 */
	@Override
	public Order getOrder(String orderId) {
		// TODO Auto-generated method stub
		// TODO 调用用户列表获取用户
		JsonResult<User> user = userFeign.getUser(8);
		// TODO 调用商品列表获取商品
		JsonResult<List<Item>> items = itemFeign.getItems(orderId);
		
		Order order = new Order();
		order.setId(orderId);
		order.setItems(items.getData());
		order.setUser(user.getData());
		
		return order;
	}

	@Override
	public void addOrder(Order order) {
		// TODO 调用用户 增加积分
		userFeign.addScore(8, 100);
		
		// TODO 调用商品 减少库存
		itemFeign.decreaseNumber(order.getItems());
		
		log.info("保存订单: "+order);
		
	}

}
