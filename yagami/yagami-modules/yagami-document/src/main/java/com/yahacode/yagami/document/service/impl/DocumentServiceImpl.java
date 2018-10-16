package com.yahacode.yagami.document.service.impl;

import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.LogUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.config.DocumentProperties;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentChain;
import com.yahacode.yagami.document.model.DocumentGroup;
import com.yahacode.yagami.document.repository.DocumentChainRepository;
import com.yahacode.yagami.document.repository.DocumentGroupRepository;
import com.yahacode.yagami.document.repository.DocumentRepository;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.utils.FileUtils;
import com.yahacode.yagami.pd.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * the implementation of DocumentService
 *
 * @author zengyongli
 */
@Service
public class DocumentServiceImpl extends BaseServiceImpl<Document> implements DocumentService {

    private DocumentRepository documentRepository;

    private DocumentGroupRepository documentGroupRepository;

    private DocumentChainRepository documentChainRepository;

    private DocumentProperties documentProperties;

    @Override
    public String addDocument(Document document) throws BizfwServiceException {
        String ext = FileUtils.getExtension(document.getName());
        document.setDownloadCount(0);
        document.setExtension(ext);
        return save(document);
    }

    @Override
    public Document saveDocument(MultipartFile file) throws BizfwServiceException {
        try {
            String fileName = file.getOriginalFilename();
            String md5 = FileUtils.getMD5(file);

            Document document = new Document();
            document.setName(fileName);
            document.setExtension(FileUtils.getExtension(document.getName()));
            document.setDownloadCount(0);
            document.setSize(file.getSize());
            document.setMd5(md5);
            document.setStatus(Document.STATUS_NORMAL);

            Document dbDocument = getByMD5(md5);
            if (dbDocument != null) {
                document.setUrl(dbDocument.getUrl());
            } else {
                String url = FileUtils.getStorageUrl(fileName, md5);
                document.setUrl(url);
                String filePath = documentProperties.getLocalStorage() + url;
                File newFile = new File(filePath);
                file.transferTo(newFile);
            }
            save(document);
            return document;
        } catch (IllegalStateException | IOException e) {
            LogUtils.error("保存文件失败", e);
            throw new BizfwServiceException(ErrorCode.Doc.File.SAVE_FILE_ERROR, e);
        }
    }

    @Transactional
    @Override
    public String saveDocuments(List<MultipartFile> files) throws BizfwServiceException {
        String documentGroupNo = StringUtils.generateUUID();
        People operator = getLoginPeople();
        for (MultipartFile file : files) {
            Document document = saveDocument(file);
            DocumentGroup documentGroup = new DocumentGroup(operator.getCode(), documentGroupNo, document
                    .getIdBfDocument());
            documentGroupRepository.save(documentGroup);
        }
        return documentGroupNo;
    }

    @Override
    public void modifyDocument(Document document) throws BizfwServiceException {
        Document dbDocument = queryById(document.getIdBfDocument());
        dbDocument.setName(document.getName());
        dbDocument.setExtension(FileUtils.getExtension(document.getName()));
        dbDocument.update(getLoginPeople().getCode());
        update(dbDocument);
    }

    @Override
    public void downloadDocument(Document document) throws BizfwServiceException {
        int downloadCount = document.getDownloadCount();
        document.setDownloadCount(downloadCount + 1);
        update(document);
    }

    @Override
    public void deleteDocument(Document document) throws BizfwServiceException {
        document.setStatus(Document.STATUS_DELETED);
        update(document);
    }

    @Override
    public void updateDocument(Document newDocument, String documentId) throws BizfwServiceException {
        DocumentChain documentChain = documentChainRepository.findByDocumentId(documentId);
        DocumentChain newChain = new DocumentChain();
        newChain.init(getLoginPeople().getCode());
        newChain.setChainNo(documentChain.getChainNo());
        newChain.setDocumentId(newDocument.getIdBfDocument());
        if (documentChain != null) {
            newChain.setRevision(documentChain.getRevision() + 1);
        } else {
            newChain.setRevision(DocumentChain.REVISION_FIRST);
        }
        documentChainRepository.save(newChain);
    }

    @Override
    public Document getByMD5(String md5) throws BizfwServiceException {
        return documentRepository.findByMd5(md5);
    }

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Autowired
    public void setDocumentGroupRepository(DocumentGroupRepository documentGroupRepository) {
        this.documentGroupRepository = documentGroupRepository;
    }

    @Autowired
    public void setDocumentChainRepository(DocumentChainRepository documentChainRepository) {
        this.documentChainRepository = documentChainRepository;
    }

    @Autowired
    public void setDocumentProperties(DocumentProperties documentProperties) {
        this.documentProperties = documentProperties;
    }

    @Override
    public JpaRepository<Document, String> getBaseRepository() {
        return documentRepository;
    }
}
