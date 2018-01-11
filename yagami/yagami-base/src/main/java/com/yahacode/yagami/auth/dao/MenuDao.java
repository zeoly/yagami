package com.yahacode.yagami.auth.dao;

import com.yahacode.yagami.auth.model.Menu;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;

/**
 * 角色dao接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface MenuDao extends BaseDao<Menu> {

	/**
	 * 获取系统根菜单
	 * 
	 * @return 根菜单
	 * @throws BizfwServiceException
	 */
	public Menu getRootMenu() throws BizfwServiceException;

}
