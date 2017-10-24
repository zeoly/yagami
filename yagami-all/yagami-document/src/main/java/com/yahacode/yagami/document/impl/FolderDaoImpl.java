package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.FolderDao;
import com.yahacode.yagami.document.model.Folder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件夹dao实现
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

}
