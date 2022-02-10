package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.core.model.Menu;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.MenuService;
import com.yahacode.yagami.core.service.PersonService;
import com.yahacode.yagami.core.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @Transactional
    @GetMapping
    public List<MenuTree> getAuthorizedMenu() {
        Person operator = personService.findByCode("admin");
        Set<Menu> menuSet = new HashSet<>();
        List<Role> roleList = operator.getRoleList();
        for (Role role : roleList) {
            menuSet.addAll(role.getMenuList());
        }
        return constructTree("root", menuSet);
    }

    private List<MenuTree> constructTree(String parentId, Set<Menu> list) {
        List<MenuTree> tops = new LinkedList<>();
        for (Menu menu : list) {
            if (parentId.equals(menu.getParentId())) {
                MenuTree tree = convert(menu);
                tree.setChildren(constructTree(tree.getId(), list));
                tops.add(tree);
            }
        }
        return tops.stream().sorted(Comparator.comparing(MenuTree::getOrders)).collect(Collectors.toList());
    }

    private MenuTree convert(Menu menu) {
        MenuTree tree = new MenuTree();
        BeanUtils.copyProperties(menu, tree);
        return tree;
    }

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
}
