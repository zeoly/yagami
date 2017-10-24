package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.dao.FolderDao;
import com.yahacode.yagami.document.dao.FolderDocRelDao;
import com.yahacode.yagami.document.dao.RoleDocumentRelDao;
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
 * 文件夹服务实现类
 *
 * @author zengyongli
 */
@Service("folderService")
public class FolderServiceImpl extends BaseServiceImpl<Folder> implements FolderService {

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private FolderDocRelDao folderDocRelDao;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private RoleDocumentRelDao roleDocumentRelDao;

    @Autowired
    private RoleService roleService;

    @Override
    public Folder getAllFolderTree() throws BizfwServiceException {
        List<Folder> folderList = folderDao.getAllFolder();
        ListUtils.sort(folderList, Folder.COLUMN_NAME);
        Folder rootFolder = folderDao.getRootFolder();
        Folder folder = convertListToTree(folderList, rootFolder);
        return folder;
    }

    @Override
    public List<Document> getDocsOfFolder(Folder folder) throws BizfwServiceException {
        List<FolderDocRelation> relations = folderDocRelDao.queryByFieldAndValue(FolderDocRelation.COLUMN_FOLDER_ID,
                folder.getIdBfFolder());
        List<Document> documents = new ArrayList<>();
        for (FolderDocRelation relation : relations) {
            Document document = documentService.queryById(relation.getFileId());
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
        folder.setPath(parentFolder.getPath() + "/" + folder.getName());
        return save(folder);
    }

    @Override
    public void modifyFolder(Folder folder) throws BizfwServiceException {
        Folder dbFolder = queryById(folder.getIdBfFolder());
        String oldPath = dbFolder.getPath();
        String newPath = oldPath.replace(dbFolder.getName(), folder.getName());
        dbFolder.setName(folder.getName());
        dbFolder.update(folder.getUpdateBy());
        update(dbFolder);
        modifyChildPath(dbFolder, oldPath, newPath);
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
     * 将文件夹列表转换为文件夹树结构
     *
     * @param list
     *         文件夹列表
     * @param rootFolder
     *         根文件夹
     * @return 文件夹树
     * @throws BizfwServiceException
     *         业务异常
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
     * 修改子文件夹的路径
     *
     * @param folder
     *         文件夹
     * @param oldPrefix
     *         修改前文件夹路径
     * @param newPrefix
     *         修改后新文件夹路径
     * @throws BizfwServiceException
     *         业务异常
     */
    private void modifyChildPath(Folder folder, String oldPrefix, String newPrefix) throws BizfwServiceException {
        String path = folder.getPath();
        String newPath = path.replace(oldPrefix, newPrefix);
        if (!path.equals(newPath)) {
            folder.setPath(newPath);
            update(folder);
        }
        List<Folder> list = folderDao.getChildFolderList(folder);
        if (ListUtils.isNotEmpty(list)) {
            for (Folder childFolder : list) {
                modifyChildPath(childFolder, oldPrefix, newPrefix);
            }
        }
    }

    /**
     * 检查文件夹是否可删除
     *
     * @param folder
     *         文件夹
     * @throws BizfwServiceException
     *         业务异常
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

}
