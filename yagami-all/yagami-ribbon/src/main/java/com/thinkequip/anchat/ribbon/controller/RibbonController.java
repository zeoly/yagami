package com.thinkequip.anchat.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class RibbonController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		String result = restTemplate.getForEntity("http://anochat-consumer-service/home", String.class).getBody();
		return result;
	}
}
