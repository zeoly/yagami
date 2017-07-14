package com.yahacode.yagami.pd.impl;

import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.pd.dao.DepartmentDao;
import com.yahacode.yagami.pd.model.Department;

/**
 * 机构信息dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

}
