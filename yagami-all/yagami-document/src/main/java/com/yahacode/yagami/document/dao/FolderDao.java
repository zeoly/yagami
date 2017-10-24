package com.yahacode.yagami.document.dao;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.document.model.Folder;

import java.util.List;

/**
 * 文件夹dao接口
 *
 * @author zengyongli
 */
public interface FolderDao extends BaseDao<Folder> {

    /**
     * 获取子文件夹数量
     *
     * @param folder
     *         文件夹
     * @return 子文件夹数量
     */
    long getChildFolderCount(Folder folder);

    /**
     * 获取所有文件夹
     *
     * @return 文件夹列表
     */
    List<Folder> getAllFolder();

    /**
     * 获取根文件夹
     *
     * @return 根文件夹
     */
    Folder getRootFolder();

    /**
     * 获取子文件夹列表
     *
     * @param folder
     *         文件夹
     * @return 子文件夹列表
     */
    List<Folder> getChildFolderList(Folder folder);

}
