package com.yahacode.yagami.pd.dao;

import java.util.List;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.DepartmentRelation;

/**
 * 机构关系dao接口
 *
 * @author zengyongli
 */
@Deprecated
public interface DepartmentRelationDao extends BaseDao<DepartmentRelation> {

    /**
     * 获取当前机构与父机构关联信息
     *
     * @param department
     *         机构
     * @return 机构关联信息
     */
    DepartmentRelation getParentDepartmentRel(Department department);

    /**
     * 获取当期机构与其所有直属子机构的关联信息
     *
     * @param department
     *         机构
     * @return 机构关联信息列表
     */
    List<DepartmentRelation> getChildDepartmentRelList(Department department);
}
