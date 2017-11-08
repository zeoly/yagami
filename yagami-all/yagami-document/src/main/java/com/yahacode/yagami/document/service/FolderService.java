package com.yahacode.yagami.document.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.model.RoleFolderAuthority;
import com.yahacode.yagami.pd.model.People;

import java.util.List;

/**
 * folder service
 *
 * @author zengyongli
 */
public interface FolderService extends BaseService<Folder> {

    /**
     * get the tree structure of all folder
     *
     * @return root folder with all children
     * @throws BizfwServiceException
     *         framework exception
     */
    Folder getAllFolderTree() throws BizfwServiceException;

    /**
     * get child folders of target folder
     *
     * @param folder
     *         target folder
     * @return the list of child folders
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Document> getDocsOfFolder(Folder folder) throws BizfwServiceException;

    /**
     * get the content of target folder , including documents and child folders
     *
     * @param folder
     *         target folder
     * @return the folder contain required content
     * @throws BizfwServiceException
     *         framework exception
     */
    Folder getContentOfFolder(Folder folder) throws BizfwServiceException;

    /**
     * add new folder
     *
     * @param folder
     *         target folder
     * @return folder pk
     * @throws BizfwServiceException
     *         if parent folder is not exists
     */
    String addFolder(Folder folder) throws BizfwServiceException;

    /**
     * modify folder name
     *
     * @param folder
     *         target folder
     * @throws BizfwServiceException
     *         framework exception
     */
    void modifyFolder(Folder folder) throws BizfwServiceException;

    /**
     * delete folder
     *
     * @param folderId
     *         folder pk
     * @param people
     *         operation people, just for log
     * @throws BizfwServiceException
     *         if target folder is not exists;
     *         if folder contains any child folder or document;
     */
    void deleteFolder(String folderId, People people) throws BizfwServiceException;

    /**
     * add a document in folder
     *
     * @param document
     *         target document
     * @param folderId
     *         folder pk
     * @return document pk
     * @throws BizfwServiceException
     *         framework exception
     */
    String addDocument(Document document, String folderId) throws BizfwServiceException;

    /**
     * delete document under folder
     *
     * @param documentId
     *         document pk
     * @param people
     *         operator
     * @throws BizfwServiceException
     *         framework exception
     */
    void deleteDocument(String documentId, People people) throws BizfwServiceException;

    /**
     * set the role authority of a folder
     *
     * @param folder
     *         target folder
     * @param roleIdList
     *         the role pk list
     * @throws BizfwServiceException
     *         framework exception
     */
    void setFolderAuthority(Folder folder, List<String> roleIdList) throws BizfwServiceException;

    /**
     * get the role authority of a folder
     *
     * @param folderId
     *         target folder pk
     * @return the list of authority
     * @throws BizfwServiceException
     *         framework exception
     */
    List<RoleFolderAuthority> getFolderAuthority(String folderId) throws BizfwServiceException;

    /**
     * get the authorized folders of people
     *
     * @param people
     *         target people
     * @return authorized folders with tree structure
     * @throws BizfwServiceException
     *         framework exception
     */
    Folder getAuthorizedFolderTree(People people) throws BizfwServiceException;
}
