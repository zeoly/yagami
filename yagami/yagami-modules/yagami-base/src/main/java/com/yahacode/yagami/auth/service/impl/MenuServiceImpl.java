package com.yahacode.yagami.auth.service.impl;

import com.yahacode.yagami.auth.model.Menu;
import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.model.RoleMenuRelation;
import com.yahacode.yagami.auth.repository.MenuRepository;
import com.yahacode.yagami.auth.repository.RoleMenuRelRepository;
import com.yahacode.yagami.auth.service.MenuService;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单服务实现类
 *
 * @author zengyongli
 * @copyright THINKEQUIP
 * @date 2017年3月19日
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleMenuRelRepository roleMenuRelRepository;

    @Override
    public Menu getAllMenuTree() throws BizfwServiceException {
        List<Menu> list = list();
        ListUtils.sort(list, Menu.COLUMN_ORDERS);
        Menu menu = convertListToTree(list, getRootMenu());
        return menu;
    }

    @Transactional
    @Override
    public void addMenu(Menu menu) throws BizfwServiceException {
        Menu parentMenu = queryById(menu.getParentMenuId());
        if (parentMenu == null) {
            throw new BizfwServiceException(ErrorCode.Auth.Menu.ADD_FAIL_WITHOUT_PARENT);
        }
        save(menu);
    }

    @Transactional
    @Override
    public void modifyMenu(Menu menu) throws BizfwServiceException {
        Menu dbMenu = queryById(menu.getIdBfMenu());
        dbMenu.setName(menu.getName());
        dbMenu.setUrl(menu.getUrl());
        dbMenu.setOrders(menu.getOrders());
        dbMenu.update(menu.getUpdateBy());
        update(dbMenu);
    }

    @Transactional
    @Override
    public void deleteMenu(String menuId) throws BizfwServiceException {
        Menu menu = queryById(menuId);
        checkCanDeleteMenu(menu);
        delete(menuId);
        deleteRoleMenuRelationByMenu(menuId);
    }

    @Transactional
    @Override
    public void setMenuOfRole(Role role, List<String> menuIdList) throws BizfwServiceException {
        deleteRoleMenuRelationByRole(role.getIdBfRole());
        for (String menuId : menuIdList) {
            Menu menu = queryById(menuId);
            if (menu == null) {
                throw new BizfwServiceException(ErrorCode.Auth.Role.SET_MENU_REL_FAIL_MENU_NOT_FOUND, menuId);
            }
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation(role.getUpdateBy(), role.getIdBfRole(), menu
                    .getIdBfMenu());
            roleMenuRelRepository.save(roleMenuRelation);
        }
    }

    @Override
    public List<Menu> getChildMenuList(Menu menu) throws BizfwServiceException {
        List<Menu> menuList = menuRepository.findByParentMenuId(menu.getIdBfMenu());
        return menuList;
    }

    @Override
    public List<Menu> getMenuListByRole(Role role) throws BizfwServiceException {
        List<Menu> menuList = new ArrayList<Menu>();
        List<RoleMenuRelation> relationList = roleMenuRelRepository.findByRoleId(role.getIdBfRole());
        for (RoleMenuRelation relation : relationList) {
            Menu menu = queryById(relation.getMenuId());
            menuList.add(menu);
        }
        return menuList;
    }

    @Override
    public Menu getMenuTreeByPeople(String peopleId) throws BizfwServiceException {
        List<Menu> resultMenuList = new ArrayList<Menu>();
        List<Role> roleList = roleService.getRoleListByPeople(peopleId);
        for (Role role : roleList) {
            List<Menu> menuList = getMenuListByRole(role);
            for (Menu menu : menuList) {
                if (!resultMenuList.contains(menu)) {
                    resultMenuList.add(menu);
                }
            }
        }
        ListUtils.sort(resultMenuList, Menu.COLUMN_ORDERS);
        Menu menu = convertListToTree(resultMenuList, getRootMenu());
        return menu;
    }

    @Override
    public Menu convertListToTree(List<Menu> list, Menu rootMenu) throws BizfwServiceException {
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu menu : list) {
            if (rootMenu.getIdBfMenu().equals(menu.getParentMenuId())) {
                Menu childMenu = convertListToTree(list, menu);
                childList.add(childMenu);
            }
        }
        rootMenu.setChildList(childList);
        return rootMenu;
    }

    @Override
    public long countChildMenu(Menu menu) throws BizfwServiceException {
        return menuRepository.countByParentMenuId(menu.getIdBfMenu());
    }

    @Override
    public void deleteRoleMenuRelationByMenu(String menuId) throws BizfwServiceException {
        roleMenuRelRepository.deleteByMenuId(menuId);
    }

    private Menu getRootMenu(){
        return menuRepository.findByName(Menu.ROOT_NAME);
    }

    /**
     * 根据角色id删除角色菜单关联关系
     *
     * @param roleId
     *         角色id
     * @throws BizfwServiceException
     */
    private void deleteRoleMenuRelationByRole(String roleId) throws BizfwServiceException {
        roleMenuRelRepository.deleteByRoleId(roleId);
    }

    /**
     * 检查菜单是否可删除
     *
     * @param menu
     *         菜单
     * @throws BizfwServiceException
     */
    private void checkCanDeleteMenu(Menu menu) throws BizfwServiceException {
        long childCount = countChildMenu(menu);
        if (childCount > 0) {
            throw new BizfwServiceException(ErrorCode.Auth.Menu.DEL_FAIL_WITH_CHILD);
        }
    }

    @Override
    public JpaRepository<Menu, String> getBaseRepository() {
        return menuRepository;
    }

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
}
