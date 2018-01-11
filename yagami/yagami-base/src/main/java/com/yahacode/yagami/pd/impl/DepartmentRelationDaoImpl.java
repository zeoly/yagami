package com.yahacode.yagami.pd.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.pd.dao.DepartmentRelationDao;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.DepartmentRelation;

/**
 * 机构关联关系dao实现类
 *
 * @author zengyongli
 */
@Repository
public class DepartmentRelationDaoImpl extends BaseDaoImpl<DepartmentRelation> implements DepartmentRelationDao {

    @Override
    public DepartmentRelation getParentDepartmentRel(Department department) {
        String hql = "from " + getTableName() + " as t where t." + DepartmentRelation.COLUMN_CHILD_DEPARTMENT_ID + " " + "= :child and t." + DepartmentRelation.COLUMN_PARENT_LEVEL + " = :level";
        Query query = createQuery(hql);
        query.setParameter("child", department.getIdBfDepartment());
        query.setParameter("level", department.getLevel() - 1);
        List<DepartmentRelation> list = query.list();
        if (ListUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<DepartmentRelation> getChildDepartmentRelList(Department department) {
        String hql = "from " + getTableName() + " as t where t." + DepartmentRelation.COLUMN_PARENT_DEPARTMENT_ID + "" +
				" = :parentId and t." + DepartmentRelation.COLUMN_PARENT_LEVEL + " = :level";
        Query query = createQuery(hql);
        query.setParameter("parentId", department.getIdBfDepartment());
        query.setParameter("level", department.getLevel());
        List<DepartmentRelation> list = query.list();
        return list;
    }
}
