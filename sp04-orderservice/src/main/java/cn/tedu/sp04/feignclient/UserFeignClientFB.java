package cn.tedu.sp04.feignclient;

import org.springframework.stereotype.Component;

import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
@Component
public class UserFeignClientFB implements UserFeignClient {

	@Override
	public JsonResult<User> getUser(Integer userId) {
		// TODO Auto-generated method stub
		if(Math.random()<0.5) {
			return JsonResult.ok(new User(1,"username","password"));
		}
		
		return JsonResult.err("获取用户失败");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		// TODO Auto-generated method stub
		return JsonResult.err("增加积分失败");
	}

}
