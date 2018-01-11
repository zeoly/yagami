package com.yahacode.yagami.document.dao;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.document.model.Folder;

import java.util.List;

/**
 * the DAO interface of Folder
 *
 * @author zengyongli
 */
public interface FolderDao extends BaseDao<Folder> {

    /**
     * get the count of child folders of the target folder
     *
     * @param folder
     *         target folder
     * @return the count of child folders
     */
    long getChildFolderCount(Folder folder);

    /**
     * get all folder in list of the database
     *
     * @return the list of folder
     */
    List<Folder> getAllFolder();

    /**
     * get the root folder
     *
     * @return root folder
     */
    Folder getRootFolder();

    /**
     * get list of child folders of the target folder
     *
     * @param folder
     *         target folder
     * @return list of child folders
     */
    List<Folder> getChildFolderList(Folder folder);

    /**
     * get folder list of authorized of a role
     *
     * @param roleId
     *         target role pk
     * @return authorized folder list
     */
    List<Folder> getAuthorizedFolderByRole(String roleId);
}
