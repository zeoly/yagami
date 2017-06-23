package com.thinkequip.anochat.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.anochat.core.model.Discuss;

@Controller
public class DiscussAction {

	@Autowired
	private DiscussService discussService;

	@RequestMapping("/list")
	@ResponseBody
	public List<Discuss> list() {
		return discussService.list();
	}
}
