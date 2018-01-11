package com.yahacode.yagami.auth.action;

import com.yahacode.yagami.auth.model.Menu;
import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.MenuService;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/menuAction")
public class MenuAction extends BaseAction {

    private MenuService menuService;

    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/addMenu.do")
    public void addMenu(Menu menu) throws BizfwServiceException {
        People people = getLoginPeople();
        menu.init(people.getCode());
        menuService.addMenu(menu);
    }

    @ResponseBody
    @RequestMapping("/modifyMenu.do")
    public void modifyMenu(Menu menu) throws BizfwServiceException {
        People people = getLoginPeople();
        menu.update(people.getCode());
        menuService.modifyMenu(menu);
    }

    @ResponseBody
    @RequestMapping("/deleteMenu.do")
    public void deleteMenu(String menuId) throws BizfwServiceException {
        menuService.deleteMenu(menuId);
    }

    @ResponseBody
    @RequestMapping("/getMenuOfRole.do")
    public List<Menu> getMenuOfRole(String roleId) throws BizfwServiceException {
        Role role = roleService.queryById(roleId);
        return menuService.getMenuListByRole(role);
    }

    @ResponseBody
    @RequestMapping("/setMenuOfRole.do")
    public void setMenuOfRole(String roleId, @RequestParam(value = "menuIdList[]") List<String> menuIdList) throws
            BizfwServiceException {
        People people = getLoginPeople();
        Role role = roleService.queryById(roleId);
        role.update(people.getCode());
        menuService.setMenuOfRole(role, menuIdList);
    }

    @RequestMapping("/getMenuTree.do")
    @ResponseBody
    public List<Menu> getMenuTree() throws BizfwServiceException {
        People people = getLoginPeople();
        Menu menu = menuService.getMenuTreeByPeople(people.getIdBfPeople());
        return menu.getChildList();
    }

    @ResponseBody
    @RequestMapping("/getAllMenu.do")
    public Menu getAllMenu() throws BizfwServiceException {
        return menuService.getAllMenuTree();
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
