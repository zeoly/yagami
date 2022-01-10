package com.yahacode.yagami.document.service.impl;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.model.FolderDocRelation;
import com.yahacode.yagami.document.model.RoleFolderAuthority;
import com.yahacode.yagami.document.repository.FolderDocRelationRepository;
import com.yahacode.yagami.document.repository.FolderRepository;
import com.yahacode.yagami.document.repository.RoleFolderAuthorityRepository;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.pd.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * the implementation of FolderService
 *
 * @author zengyongli
 */
@Service
public class FolderServiceImpl extends BaseServiceImpl<Folder> implements FolderService {

    private FolderRepository folderRepository;

    private FolderDocRelationRepository folderDocRelationRepository;

    private RoleFolderAuthorityRepository roleFolderAuthorityRepository;

    private DocumentService documentService;

    private RoleService roleService;

    @Override
    public Folder getAllFolderTree() throws BizfwServiceException {
        List<Folder> folderList = folderRepository.findAll();
        Folder rootFolder = folderRepository.findByName(Folder.ROOT_NAME);
        return convertListToTree(folderList, rootFolder);
    }

    @Override
    public List<Document> getDocsOfFolder(Folder folder) throws BizfwServiceException {
        List<FolderDocRelation> relations = folderDocRelationRepository.findByFolderId(folder.getIdBfFolder());
        List<Document> documents = new ArrayList<>();
        for (FolderDocRelation relation : relations) {
            Document document = documentService.queryById(relation.getDocumentId());
            documents.add(document);
        }
        return documents;
    }

    @Override
    public Folder getContentOfFolder(Folder folder) throws BizfwServiceException {
        List<Folder> folders = folderRepository.findAllByParentIdOrderByName(folder.getIdBfFolder());
        List<Document> documents = getDocsOfFolder(folder);
        folder.setChildFolderList(folders);
        folder.setDocumentList(documents);
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
    public void deleteFolder(String folderId) throws BizfwServiceException {
        Folder folder = queryById(folderId);
        checkObjectNotNull(folder, "文件夹[" + folderId + "]", "删除文件夹");
        checkCanDeleteFolder(folder);
        delete(folderId);
    }

    @Transactional
    @Override
    public String addDocument(MultipartFile file, String folderId) throws BizfwServiceException {
        Person loginPeople = getLoginPeople();
        Document document = documentService.saveDocument(file);
        FolderDocRelation folderDocRelation = new FolderDocRelation(loginPeople.getCode(), folderId, document
                .getIdBfDocument());
        folderDocRelationRepository.save(folderDocRelation);
        return document.getIdBfDocument();
    }

    @Transactional
    @Override
    public void deleteDocument(String documentId) throws BizfwServiceException {
        Document document = documentService.queryById(documentId);
        document.update(getLoginPeople().getCode());
        documentService.deleteDocument(document);

        folderDocRelationRepository.deleteByDocumentId(documentId);
    }

    @Transactional
    @Override
    public void setFolderAuthority(String folderId, List<String> roleIdList) throws BizfwServiceException {
        roleFolderAuthorityRepository.deleteByFolderId(folderId);
        for (String roleId : roleIdList) {
            RoleFolderAuthority roleFolderAuthority = new RoleFolderAuthority(getLoginPeople().getCode(), roleId,
                    folderId);
            roleFolderAuthorityRepository.save(roleFolderAuthority);
        }
    }

    @Override
    public List<Role> getFolderAuthority(String folderId) throws BizfwServiceException {
        List<RoleFolderAuthority> list = roleFolderAuthorityRepository.findAllByFolderId(folderId);
        List<Role> roles = new ArrayList<>();
        for (RoleFolderAuthority authority : list) {
            Role role = roleService.queryById(authority.getRoleId());
            roles.add(role);
        }
        return roles;
    }

    @Override
    public Folder getAuthorizedFolderTree(Person people) throws BizfwServiceException {
        List<Folder> folders = getAuthorizedFolderList(people.getIdBfPeople());
        replenishAuthFolderList(folders);
        ListUtils.sort(folders, Folder.COLUMN_NAME);
        Folder rootFolder = folderRepository.findByName(Folder.ROOT_NAME);
        return convertListToTree(folders, rootFolder);
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
        long childFolderCount = folderRepository.countByParentId(folder.getIdBfFolder());
        if (childFolderCount > 0) {
            throw new BizfwServiceException(ErrorCode.Doc.Folder.DEL_FAIL_WITH_CHILD_FOLDER);
        }
        long childDocumentCount = folderDocRelationRepository.countByFolderId(folder.getIdBfFolder());
        if (childDocumentCount > 0) {
            throw new BizfwServiceException(ErrorCode.Doc.Folder.DEL_FAIL_WITH_CHILD_DOC);
        }
    }

    /**
     * get authorized folder list of people
     *
     * @param peopleId
     *         people pk
     * @return authorized folder list
     * @throws BizfwServiceException
     *         framework exception
     */
    private List<Folder> getAuthorizedFolderList(String peopleId) throws BizfwServiceException {
        List<Folder> authorizedFolders = new ArrayList<>();
        List<Role> roles = roleService.getRoleListByPeople(peopleId);
        for (Role role : roles) {
            List<Folder> folders = folderRepository.findAllAuthorizedFolderByRoleId(role.getIdBfRole());
            for (Folder folder : folders) {
                if (!authorizedFolders.contains(folder)) {
                    authorizedFolders.add(folder);
                }
            }
        }
        return authorizedFolders;
    }

    /**
     * replenish the auth folder list with their parent folder which not authorized
     *
     * @param authFolderList
     *         the list of authorized folder
     * @throws BizfwServiceException
     *         framework exception
     */
    private void replenishAuthFolderList(List<Folder> authFolderList) throws BizfwServiceException {
        List<Folder> parentFolderList = new ArrayList<>();
        for (Folder folder : authFolderList) {
            List<Folder> tempParentFolderList = getParentFolders(folder);
            parentFolderList.addAll(tempParentFolderList);
        }
        for (Folder folder : parentFolderList) {
            if (!authFolderList.contains(folder)) {
                authFolderList.add(folder);
            }
        }
    }

    /**
     * get the parent folders, including cross level
     *
     * @param childFolder
     *         target folder
     * @return parent folder list
     * @throws BizfwServiceException
     *         framework exception
     */
    private List<Folder> getParentFolders(Folder childFolder) throws BizfwServiceException {
        List<Folder> parentFolderList = new ArrayList<>();
        while (StringUtils.isNotEmpty(childFolder.getParentId())) {
            Folder parentFolder = queryById(childFolder.getParentId());
            parentFolderList.add(parentFolder);
            childFolder = parentFolder;
        }
        return parentFolderList;
    }

    @Autowired
    public void setFolderRepository(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Autowired
    public void setFolderDocRelationRepository(FolderDocRelationRepository folderDocRelationRepository) {
        this.folderDocRelationRepository = folderDocRelationRepository;
    }

    @Autowired
    public void setRoleFolderAuthorityRepository(RoleFolderAuthorityRepository roleFolderAuthorityRepository) {
        this.roleFolderAuthorityRepository = roleFolderAuthorityRepository;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public JpaRepository<Folder, String> getBaseRepository() {
        return folderRepository;
    }
}
