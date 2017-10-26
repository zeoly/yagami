package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.dao.FolderDao;
import com.yahacode.yagami.document.dao.FolderDocRelDao;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.model.FolderDocRelation;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.pd.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * the implementation of FolderService
 *
 * @author zengyongli
 */
@Service
public class FolderServiceImpl extends BaseServiceImpl<Folder> implements FolderService {

    private FolderDao folderDao;

    private FolderDocRelDao folderDocRelDao;

    private DocumentService documentService;

    @Override
    public Folder getAllFolderTree() throws BizfwServiceException {
        List<Folder> folderList = folderDao.getAllFolder();
        ListUtils.sort(folderList, Folder.COLUMN_NAME);
        Folder rootFolder = folderDao.getRootFolder();
        return convertListToTree(folderList, rootFolder);
    }

    @Override
    public List<Document> getDocsOfFolder(Folder folder) throws BizfwServiceException {
        List<FolderDocRelation> relations = folderDocRelDao.queryByFieldAndValue(FolderDocRelation.COLUMN_FOLDER_ID,
                folder.getIdBfFolder());
        List<Document> documents = new ArrayList<>();
        for (FolderDocRelation relation : relations) {
            Document document = documentService.queryById(relation.getDocumentId());
            documents.add(document);
        }
        return documents;
    }

    @Override
    public Folder getContentOfFolder(Folder folder) throws BizfwServiceException {
        List<Folder> folders = folderDao.getChildFolderList(folder);
        List<Document> documents = getDocsOfFolder(folder);
        folder.setChildFolderList(folders);
        folder.setFileList(documents);
        return folder;
    }

    @Override
    public String addFolder(Folder folder) throws BizfwServiceException {
        Folder parentFolder = queryById(folder.getParentId());
        checkObjectNotNull(parentFolder, "文件夹[" + folder.getParentId() + "]", "新增文件夹");
        return save(folder);
    }

    @Override
    public void modifyFolder(Folder folder) throws BizfwServiceException {
        Folder dbFolder = queryById(folder.getIdBfFolder());
        dbFolder.setName(folder.getName());
        dbFolder.update(folder.getUpdateBy());
        update(dbFolder);
    }

    @Override
    public void deleteFolder(String folderId, People people) throws BizfwServiceException {
        Folder folder = queryById(folderId);
        checkObjectNotNull(folder, "文件夹[" + folderId + "]", "删除文件夹");
        checkCanDeleteFolder(folder);
        delete(folderId);
    }

    @Transactional
    @Override
    public String addDocument(Document document, String folderId) throws BizfwServiceException {
        String documentId = documentService.addDocument(document);
        FolderDocRelation relation = new FolderDocRelation(document.getUpdateBy(), folderId, documentId);
        folderDocRelDao.save(relation);
        return documentId;
    }

    /**
     * convert folder list to the tree structure
     *
     * @param list
     *         folder list
     * @param rootFolder
     *         the root folder in the folder list
     * @return root folder with its child folders
     * @throws BizfwServiceException
     *         framework exception
     */
    private Folder convertListToTree(List<Folder> list, Folder rootFolder) throws BizfwServiceException {
        List<Folder> childList = new ArrayList<>();
        for (Folder folder : list) {
            if (rootFolder.getIdBfFolder().equals(folder.getParentId())) {
                Folder childFolder = convertListToTree(list, folder);
                childList.add(childFolder);
            }
        }
        rootFolder.setChildFolderList(childList);
        return rootFolder;
    }


    /**
     * check whether the folder can delete, will throw exception if not
     *
     * @param folder
     *         target folder
     * @throws BizfwServiceException
     *         if contain any child folder;
     *         if contain any document;
     */
    private void checkCanDeleteFolder(Folder folder) throws BizfwServiceException {
        long childFolderCount = folderDao.getChildFolderCount(folder);
        if (childFolderCount > 0) {
            throw new BizfwServiceException(ErrorCode.Doc.Folder.DEL_FAIL_WITH_CHILD_FOLDER);
        }
        long childDocumentCount = folderDocRelDao.getCountByFieldAndValue(FolderDocRelation.COLUMN_FOLDER_ID, folder
                .getIdBfFolder());
        if (childDocumentCount > 0) {
            throw new BizfwServiceException(ErrorCode.Doc.Folder.DEL_FAIL_WITH_CHILD_DOC);
        }
    }

    @Override
    public BaseDao<Folder> getBaseDao() {
        return folderDao;
    }

    @Autowired
    public void setFolderDao(FolderDao folderDao) {
        this.folderDao = folderDao;
    }

    @Autowired
    public void setFolderDocRelDao(FolderDocRelDao folderDocRelDao) {
        this.folderDocRelDao = folderDocRelDao;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
