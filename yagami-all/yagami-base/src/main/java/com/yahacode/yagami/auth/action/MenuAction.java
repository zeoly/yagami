package com.yahacode.yagami.auth.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.auth.model.Menu;
import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.MenuService;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/menuAction")
public class MenuAction extends BaseAction {

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/addMenu.do")
	public String addMenu(HttpServletRequest request, Menu menu) throws BizfwServiceException {
		People people = getLoginPeople(request);
		menu.init(people.getCode());
		menuService.addMenu(menu);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyMenu.do")
	public String modifyMenu(HttpServletRequest request, Menu menu) throws BizfwServiceException {
		People people = getLoginPeople(request);
		menu.update(people.getCode());
		menuService.modifyMenu(menu);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteMenu.do")
	public String deleteMenu(String menuId) throws BizfwServiceException {
		menuService.deleteMenu(menuId);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getMenuOfRole.do")
	public List<Menu> getMenuOfRole(String roleId) throws BizfwServiceException {
		Role role = roleService.queryById(roleId);
		List<Menu> menuList = menuService.getMenuListByRole(role);
		return menuList;
	}

	@ResponseBody
	@RequestMapping("/setMenuOfRole.do")
	public String setMenuOfRole(HttpServletRequest request, String roleId,
			@RequestParam(value = "menuIdList[]") List<String> menuIdList) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Role role = roleService.queryById(roleId);
		role.update(people.getCode());
		menuService.setMenuOfRole(role, menuIdList);
		return SUCCESS;
	}

	@RequestMapping("/getMenuTree.do")
	@ResponseBody
	public List<Menu> getMenuTree(HttpServletRequest request) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Menu menu = menuService.getMenuTreeByPeople(people.getIdBfPeople());
		return menu.getChildList();
	}

	@ResponseBody
	@RequestMapping("/getAllMenu.do")
	public Menu getAllMenu() throws BizfwServiceException {
		return menuService.getAllMenuTree();
	}
}
