package cn.tedu.sp09.feignclient;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
@Component
public class ItemFeignClientFB implements ItemFeignClient {

	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		// TODO Auto-generated method stub
		return JsonResult.err("无法获取订单商品列表");

	}

	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		// TODO Auto-generated method stub
		return JsonResult.err("无法修改商品库存");

	}

}
