package cn.tedu.sp09.feignclient;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
@Component
public class OrderFeignClientFB implements OrderFeignClient {

	@Override
	public JsonResult<Order> getOrder(String orderId) {
		// TODO Auto-generated meth
		return JsonResult.err("无法获取商品订单");
	}

	@Override
	public JsonResult addOrder() {
		return JsonResult.err("无法保存订单");
	}

}
