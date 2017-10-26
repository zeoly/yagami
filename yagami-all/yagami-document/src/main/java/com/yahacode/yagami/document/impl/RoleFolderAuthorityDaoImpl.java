package com.yahacode.yagami.document.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.RoleFolderAuthorityDao;
import com.yahacode.yagami.document.model.RoleFolderAuthority;

/**
 * the implementation of RoleFolderAuthorityDao
 *
 * @author zengyongli
 */
@Repository
public class RoleFolderAuthorityDaoImpl extends BaseDaoImpl<RoleFolderAuthority> implements RoleFolderAuthorityDao {

    @Override
    public RoleFolderAuthority getAuthority(String roleId, String folderId) {
        String hql = "from " + getTableName() + " where " + RoleFolderAuthority.COLUMN_ROLE_ID + " = :" +
                RoleFolderAuthority.COLUMN_ROLE_ID + " and " + RoleFolderAuthority.COLUMN_FOLDER_ID + " = :" +
                RoleFolderAuthority.COLUMN_FOLDER_ID;
        Query query = createQuery(hql);
        query.setParameter(RoleFolderAuthority.COLUMN_ROLE_ID, roleId);
        query.setParameter(RoleFolderAuthority.COLUMN_FOLDER_ID, folderId);
        List<RoleFolderAuthority> list = query.list();
        if (ListUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
