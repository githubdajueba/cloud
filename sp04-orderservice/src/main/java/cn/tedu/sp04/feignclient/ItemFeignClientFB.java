package cn.tedu.sp04.feignclient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;

@Component
public class ItemFeignClientFB implements ItemFeignClient{

	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		// TODO Auto-generated method stub
		if(Math.random()<0.5) {
		List<Item> list = new ArrayList<>();
		list.add(new Item(1,"商品1",1));
		list.add(new Item(2,"商品2",2));
		list.add(new Item(3,"商品3",3));
		list.add(new Item(4,"商品4",4));
		return JsonResult.ok(list);
		}
		return JsonResult.err("获取商品列表失败");
	}

	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		// TODO Auto-generated method stub
		return JsonResult.err("减少库存失败");
	}

}
