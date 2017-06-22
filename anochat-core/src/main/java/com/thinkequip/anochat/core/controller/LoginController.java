package com.thinkequip.anochat.core.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class LoginController {

	@RequestMapping("/home")
	@ResponseBody
	String home() {
		return "jjj";
	}
}
