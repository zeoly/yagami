package com.thinkequip.anochat.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConsumerController {

	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		System.out.println("consumer");
		return "consumer";
	}
}
