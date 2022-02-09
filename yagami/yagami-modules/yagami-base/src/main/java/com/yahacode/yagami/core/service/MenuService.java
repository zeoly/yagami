package com.yahacode.yagami.core.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Menu;

import java.util.List;

/**
 * menu service
 *
 * @author zengyongli
 * @since 2017/03/19
 */
public interface MenuService extends BaseService<Menu> {

    List<Menu> findByParentId(String parentId);

    /**
     * add menu
     *
     * @param menu 菜单
     * @throws ServiceException
     */
    void addMenu(Menu menu) throws ServiceException;
//
//    /**
//     * 修改菜单
//     *
//     * @param menu 菜单
//     * @throws ServiceException
//     */
//    public void modifyMenu(Menu menu) throws ServiceException;
//
//    /**
//     * 根据id删除菜单
//     *
//     * @param menuId 菜单id
//     * @throws ServiceException
//     */
//    public void deleteMenu(String menuId) throws ServiceException;
//
//    /**
//     * 设置角色对应菜单组
//     *
//     * @param role       角色
//     * @param menuIdList 菜单列表
//     * @throws ServiceException
//     */
//    public void setMenuOfRole(Role role, List<String> menuIdList) throws ServiceException;
//
//    /**
//     * 获取角色对应的菜单列表
//     *
//     * @param role 角色
//     * @return 菜单列表
//     * @throws ServiceException
//     */
//    public List<Menu> getMenuListByRole(Role role) throws ServiceException;
//
//    /**
//     * 获取人员对应的菜单树
//     *
//     * @param peopleId 人员id
//     * @return 菜单树
//     * @throws ServiceException
//     */
//    public Menu getMenuTreeByPeople(String peopleId) throws ServiceException;
//
//    /**
//     * 将菜单列表转换为菜单树结构
//     *
//     * @param list     菜单列表
//     * @param rootMenu 根菜单
//     * @return 菜单树
//     * @throws ServiceException
//     */
//    public Menu convertListToTree(List<Menu> list, Menu rootMenu) throws ServiceException;
//
//    /**
//     * 获取当前菜单下子菜单数量
//     *
//     * @param menu 菜单
//     * @return 子菜单数量
//     * @throws ServiceException
//     */
//    public long countChildMenu(Menu menu) throws ServiceException;
//
//    /**
//     * 删除与菜单关联的角色关系
//     *
//     * @param menuId 菜单id
//     * @throws ServiceException
//     */
//    public void deleteRoleMenuRelationByMenu(String menuId) throws ServiceException;

}
