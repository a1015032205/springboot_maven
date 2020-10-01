package com.springboot.md.controller;

/**
 * @Author 秒度
 * @Email fangxin.md@Gmail.com
 * @Date 2020/9/30 下午3:12
 * @Description
 */

import com.springboot.md.config.AbstracController;
import com.springboot.md.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/demo"})
public class DemoController extends AbstracController {

	@GetMapping
	public Object demo(){
        ApiException.create(200, "暂无数据");
		System.out.println("xxxxxxxxxxxx");
		return setSuccessModelMap();
	}

}
