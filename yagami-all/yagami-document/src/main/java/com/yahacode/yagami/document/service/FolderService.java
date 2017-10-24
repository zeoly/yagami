package com.yahacode.yagami.document.service;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.pd.model.People;

import java.util.List;

/**
 * 文件夹服务接口
 *
 * @author zengyongli
 */
public interface FolderService extends BaseService<Folder> {

    /**
     * 获取所有所有文件夹
     *
     * @return 文件夹树结构
     * @throws BizfwServiceException
     *         业务异常
     */
    Folder getAllFolderTree() throws BizfwServiceException;

    /**
     * 获取文件夹下文件
     *
     * @param folder
     *         文件夹
     * @return 文件列表
     * @throws BizfwServiceException
     */
    List<Document> getDocsOfFolder(Folder folder) throws BizfwServiceException;

    /**
     * 获取文件夹下内容，包含文件夹及文件
     *
     * @param folder
     *         文件夹
     * @return 文档内容
     * @throws BizfwServiceException
     *         业务异常
     */
    Folder getContentOfFolder(Folder folder) throws BizfwServiceException;

    /**
     * 新增文件夹
     *
     * @param folder
     *         文件夹
     * @return 文件夹主键
     * @throws BizfwServiceException
     *         业务异常
     */
    String addFolder(Folder folder) throws BizfwServiceException;

    /**
     * 修改文件夹
     *
     * @param folder
     *         文件夹
     * @throws BizfwServiceException
     *         业务异常
     */
    void modifyFolder(Folder folder) throws BizfwServiceException;

    /**
     * 删除文件夹
     *
     * @param folderId
     *         文件夹id
     * @param people
     *         操作人员
     * @throws BizfwServiceException
     *         业务异常
     */
    void deleteFolder(String folderId, People people) throws BizfwServiceException;

    /**
     * 在文件夹下新增文件
     *
     * @param document
     *         文件
     * @param folderId
     *         文件夹id
     * @return 文件id
     * @throws BizfwServiceException
     *         业务异常
     */
    String addDocument(Document document, String folderId) throws BizfwServiceException;
}
