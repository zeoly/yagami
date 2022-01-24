//package com.yahacode.yagami.core.controller;
//
//import com.yahacode.yagami.core.model.Menu;
//import com.yahacode.yagami.core.model.Role;
//import com.yahacode.yagami.core.service.MenuService;
//import com.yahacode.yagami.core.service.RoleService;
//import com.yahacode.yagami.base.BaseController;
//import com.yahacode.yagami.base.ServiceException;
//import com.yahacode.yagami.core.model.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/menuAction")
//public class MenuAction extends BaseController {
//
//    private MenuService menuService;
//
//    private RoleService roleService;
//
//    @ResponseBody
//    @RequestMapping("/addMenu.do")
//    public void addMenu(Menu menu) throws ServiceException {
//        Person people = getLoginPeople();
//        menu.init(people.getCode());
//        menuService.addMenu(menu);
//    }
//
//    @ResponseBody
//    @RequestMapping("/modifyMenu.do")
//    public void modifyMenu(Menu menu) throws ServiceException {
//        Person people = getLoginPeople();
//        menu.update(people.getCode());
//        menuService.modifyMenu(menu);
//    }
//
//    @ResponseBody
//    @RequestMapping("/deleteMenu.do")
//    public void deleteMenu(String menuId) throws ServiceException {
//        menuService.deleteMenu(menuId);
//    }
//
//    @ResponseBody
//    @RequestMapping("/getMenuOfRole.do")
//    public List<Menu> getMenuOfRole(String roleId) throws ServiceException {
//        Role role = roleService.queryById(roleId);
//        return menuService.getMenuListByRole(role);
//    }
//
//    @ResponseBody
//    @RequestMapping("/setMenuOfRole.do")
//    public void setMenuOfRole(String roleId, @RequestParam(value = "menuIdList[]") List<String> menuIdList) throws ServiceException {
//        Person people = getLoginPeople();
//        Role role = roleService.queryById(roleId);
//        role.update(people.getCode());
//        menuService.setMenuOfRole(role, menuIdList);
//    }
//
//    @RequestMapping("/getMenuTree.do")
//    @ResponseBody
//    public List<Menu> getMenuTree() throws ServiceException {
//        Person people = getLoginPeople();
//        Menu menu = menuService.getMenuTreeByPeople(people.getIdBfPeople());
//        return menu.getChildList();
//    }
//
//    @ResponseBody
//    @RequestMapping("/getAllMenu.do")
//    public Menu getAllMenu() throws ServiceException {
//        return menuService.getAllMenuTree();
//    }
//
//    @Autowired
//    public void setMenuService(MenuService menuService) {
//        this.menuService = menuService;
//    }
//
//    @Autowired
//    public void setRoleService(RoleService roleService) {
//        this.roleService = roleService;
//    }
//}
