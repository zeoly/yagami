package com.yahacode.yagami.document.dao;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.document.model.RoleFolderAuthority;

/**
 * the DAO interface of RoleFolderAuthority
 *
 * @author zengyongli
 */
public interface RoleFolderAuthorityDao extends BaseDao<RoleFolderAuthority> {

    /**
     * get the authority entity of the role and folder relation
     *
     * @param roleId
     *         the pk of role
     * @param folderId
     *         the pk of folder
     * @return authority entity
     */
    RoleFolderAuthority getAuthority(String roleId, String folderId);

    /**
     * delete the relation by folder pk
     *
     * @param folderId
     *         target folder pk
     */
    void deleteByFolder(String folderId);
}
