package com.yahacode.yagami.core.service.impl;

import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.core.model.Menu;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.repository.MenuRepository;
import com.yahacode.yagami.core.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MenuService implementation
 *
 * @author zengyongli
 * @date 2017/03/19
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findByParentId(String parentId) {
        return menuRepository.findByParentIdOrderByOrders(parentId);
    }

    @Override
    public void addMenu(Menu menu) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} add menu [{}] start", person.getCode(), menu.getName());
        if (menu.getParentId() != null) {
            checkMenuExists(menu.getParentId());
        }
        initAndSave(menu);
        log.info("{} add menu [{}] end", person.getCode(), menu.getName());
    }

    private void checkMenuExists(String menuId) throws ServiceException {
        Menu menu = findById(menuId);
        if (menu != null) {
            log.warn("menu id {} not exists");
            throw new ServiceException("");
        }
    }

    @Override
    public void modifyMenu(Menu menu) throws ServiceException {
        Menu dbMenu = findById(menu.getId());
        dbMenu.setName(menu.getName());
        dbMenu.setUrl(menu.getUrl());
        dbMenu.setOrders(menu.getOrders());
        updateById(dbMenu);
    }

//    @Override
//    public void deleteMenu(String menuId) throws ServiceException {
//        Menu menu = queryById(menuId);
//        checkCanDeleteMenu(menu);
//        delete(menuId);
//        deleteRoleMenuRelationByMenu(menuId);
//    }
//
//    @Override
//    public void setMenuOfRole(Role role, List<String> menuIdList) throws ServiceException {
//        deleteRoleMenuRelationByRole(role.getIdBfRole());
//        for (String menuId : menuIdList) {
//            Menu menu = queryById(menuId);
//            if (menu == null) {
//                throw new ServiceException(ErrorCode.Auth.Role.SET_MENU_REL_FAIL_MENU_NOT_FOUND, menuId);
//            }
//            RoleMenuRelation roleMenuRelation = new RoleMenuRelation(role.getUpdateBy(), role.getIdBfRole(), menu
//                    .getIdBfMenu());
//            roleMenuRelRepository.save(roleMenuRelation);
//        }
//    }
//
//    @Override
//    public List<Menu> getMenuListByRole(Role role) throws ServiceException {
//        List<Menu> menuList = new ArrayList<Menu>();
//        List<RoleMenuRelation> relationList = roleMenuRelRepository.findByRoleId(role.getIdBfRole());
//        for (RoleMenuRelation relation : relationList) {
//            Menu menu = queryById(relation.getMenuId());
//            menuList.add(menu);
//        }
//        return menuList;
//    }
//
//    @Override
//    public Menu getMenuTreeByPeople(String peopleId) throws ServiceException {
//        List<Menu> resultMenuList = new ArrayList<Menu>();
//        List<Role> roleList = roleService.getRoleListByPeople(peopleId);
//        for (Role role : roleList) {
//            List<Menu> menuList = getMenuListByRole(role);
//            for (Menu menu : menuList) {
//                if (!resultMenuList.contains(menu)) {
//                    resultMenuList.add(menu);
//                }
//            }
//        }
//        ListUtils.sort(resultMenuList, Menu.COLUMN_ORDERS);
//        Menu menu = convertListToTree(resultMenuList, getRootMenu());
//        return menu;
//    }
//
//    @Override
//    public Menu convertListToTree(List<Menu> list, Menu rootMenu) throws ServiceException {
//        List<Menu> childList = new ArrayList<Menu>();
//        for (Menu menu : list) {
//            if (rootMenu.getIdBfMenu().equals(menu.getParentMenuId())) {
//                Menu childMenu = convertListToTree(list, menu);
//                childList.add(childMenu);
//            }
//        }
//        rootMenu.setChildList(childList);
//        return rootMenu;
//    }
//
//    @Override
//    public long countChildMenu(Menu menu) throws ServiceException {
//        return menuRepository.countByParentMenuId(menu.getIdBfMenu());
//    }
//
//    @Override
//    public void deleteRoleMenuRelationByMenu(String menuId) throws ServiceException {
//        roleMenuRelRepository.deleteByMenuId(menuId);
//    }
//
//    private Menu getRootMenu() {
//        return menuRepository.findByName(Menu.ROOT_NAME);
//    }
//
//    /**
//     * 根据角色id删除角色菜单关联关系
//     *
//     * @param roleId 角色id
//     * @throws ServiceException
//     */
//    private void deleteRoleMenuRelationByRole(String roleId) throws ServiceException {
//        roleMenuRelRepository.deleteByRoleId(roleId);
//    }
//
//    /**
//     * 检查菜单是否可删除
//     *
//     * @param menu 菜单
//     * @throws ServiceException
//     */
//    private void checkCanDeleteMenu(Menu menu) throws ServiceException {
//        long childCount = countChildMenu(menu);
//        if (childCount > 0) {
//            throw new ServiceException(ErrorCode.Auth.Menu.DEL_FAIL_WITH_CHILD);
//        }
//    }

    @Override
    public JpaRepository<Menu, String> getBaseRepository() {
        return menuRepository;
    }

}
