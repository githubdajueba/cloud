package cn.tedu.sp09.feignclient;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
/**
 * feign + hystrix 降级
	feign 远程接口中指定降级类
 	远程调用失败, 会执行降级类中的代码
 * @author 000
 *
 */
@Component
public class UserFeignClientFB implements UserFeignClient {

	@Override
	public JsonResult<User> getUser(Integer userId) {
		// TODO Auto-generated method stub
		return JsonResult.err("无法获取用户信息");

	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		// TODO Auto-generated method stub
		return JsonResult.err("无法增加用户积分");
	}

}
