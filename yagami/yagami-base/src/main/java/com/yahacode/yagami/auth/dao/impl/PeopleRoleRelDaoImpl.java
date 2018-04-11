package com.yahacode.yagami.auth.dao.impl;

import org.springframework.stereotype.Repository;

import com.yahacode.yagami.auth.dao.PeopleRoleRelDao;
import com.yahacode.yagami.auth.model.PeopleRoleRelation;
import com.yahacode.yagami.base.impl.BaseDaoImpl;

/**
 * 人员角色关联关系dao实现
 *
 * @author zengyongli
 * @copyright THINKEQUIP
 * @date 2017年3月19日
 */
@Deprecated
@Repository("peopleRoleRelDao")
public class PeopleRoleRelDaoImpl extends BaseDaoImpl<PeopleRoleRelation> implements PeopleRoleRelDao {

}
