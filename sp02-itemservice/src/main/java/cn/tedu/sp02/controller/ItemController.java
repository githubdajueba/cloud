package cn.tedu.sp02.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ItemController {
	@Value("${server.port}")
	private int port;
	@Autowired
	private ItemService itemService;
	@RequestMapping("/{orderId}")
	//@GetMapping("/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId){
		log.info("orderId="+orderId+",port="+port);
		/**
		 * item-service 的 ItemController 添加延迟代码，以便测试 ribbon 的重试机制
		 */
		if(Math.random()<0.6) {
			int t = new Random().nextInt(5000);
			System.out.println("dalay: "+t);
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
			}
		}
		
		List<Item> items = itemService.getItems(orderId);	
		return JsonResult.ok(items).msg("port="+port);
	}
	/**
	 * 当采用@PostMapping时,可以采用表单提交;
	 * 否则不会出现预期结果;
	 * 也可使用postman工具,设定参数进行模拟提交测试
	 * @param items
	 * @return
	 */
	//@RequestMapping("decreaseNumber")
	@PostMapping("/decreaseNumber")
	public JsonResult<?> decreasseNumber(@RequestBody List<Item> items){
		itemService.decreaseNumbers(items);
		return JsonResult.ok();//200
	}
}
