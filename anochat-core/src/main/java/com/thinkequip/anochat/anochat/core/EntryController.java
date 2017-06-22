package com.thinkequip.anochat.anochat.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class EntryController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "jjj";
	}

	public static void main(String[] args) {
		SpringApplication.run(EntryController.class, args);
	}
}
