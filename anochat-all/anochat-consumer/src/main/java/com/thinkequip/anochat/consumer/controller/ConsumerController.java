package com.thinkequip.anochat.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsumerController {

	@RequestMapping("/home")
	public String home() {
		return "consumer";
	}
}
