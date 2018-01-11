package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.FolderDao;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.model.RoleFolderAuthority;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * the implementation of FolderDao
 *
 * @author zengyongli
 */
@Repository
public class FolderDaoImpl extends BaseDaoImpl<Folder> implements FolderDao {

    @Override
    public long getChildFolderCount(Folder folder) {
        return getCountByFieldAndValue(Folder.COLUMN_PARENT_ID, folder.getIdBfFolder());
    }

    @Override
    public List<Folder> getAllFolder() {
        return list();
    }

    @Override
    public Folder getRootFolder() {
        List<Folder> list = queryByFieldAndValue(Folder.COLUMN_NAME, Folder.ROOT_NAME);
        if (ListUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Folder> getChildFolderList(Folder folder) {
        return queryByFieldAndValue(Folder.COLUMN_PARENT_ID, folder.getIdBfFolder());
    }

    @Override
    public List<Folder> getAuthorizedFolderByRole(String roleId) {
        String authTable = RoleFolderAuthority.class.getSimpleName();
        String hql = "select f from " + getTableName() + " f, " + authTable + " a where a." + RoleFolderAuthority
                .COLUMN_ROLE_ID + " = :" + RoleFolderAuthority.COLUMN_ROLE_ID + " and f." + Folder.COLUMN_ID + " = a"
                + "." + RoleFolderAuthority.COLUMN_FOLDER_ID;
        Query query = createQuery(hql);
        query.setParameter(RoleFolderAuthority.COLUMN_ROLE_ID, roleId);
        return query.list();
    }

}
