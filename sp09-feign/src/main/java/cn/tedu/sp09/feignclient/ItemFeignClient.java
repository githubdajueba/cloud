package cn.tedu.sp09.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;

@FeignClient(name="item-service",fallback =ItemFeignClientFB.class )
public interface ItemFeignClient {
	/**
	 * 注意,如果请求参数名与方法参数名不同,@RequestParam不能省略,并且要指定请求参数名:
    @RequestParam("score") Integer s
	 * @param orderId
	 * @return
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);

	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
