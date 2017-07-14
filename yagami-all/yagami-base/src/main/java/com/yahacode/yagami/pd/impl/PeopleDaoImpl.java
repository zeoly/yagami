package com.yahacode.yagami.pd.impl;

import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.pd.dao.PeopleDao;
import com.yahacode.yagami.pd.model.People;

/**
 * 人员信息dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("peopleDao")
public class PeopleDaoImpl extends BaseDaoImpl<People> implements PeopleDao {

}
