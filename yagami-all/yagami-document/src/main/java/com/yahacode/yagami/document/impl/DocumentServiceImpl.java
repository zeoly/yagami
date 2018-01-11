package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.dao.DocumentChainDao;
import com.yahacode.yagami.document.dao.DocumentDao;
import com.yahacode.yagami.document.dao.DocumentGroupDao;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentChain;
import com.yahacode.yagami.document.model.DocumentGroup;
import com.yahacode.yagami.document.service.DocumentService;
import com.yahacode.yagami.document.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DocumentDao documentDao;

    private DocumentChainDao documentChainDao;

    private DocumentGroupDao documentGroupDao;

    private FileUtils fileUtils;

    @Override
    public String addDocument(Document document) throws BizfwServiceException {
        String ext = FileUtils.getExtension(document.getName());
        document.setDownloadCount(0);
        document.setExtension(ext);
        return save(document);
    }

    @Override
    public Document saveDocument(MultipartFile file, String peopleCode) throws BizfwServiceException {
        try {
            String fileName = file.getOriginalFilename();
            String md5 = FileUtils.getMD5(file);

            Document document = new Document(peopleCode);
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
                String url = fileUtils.getStorageUrl(fileName, md5);
                document.setUrl(url);
                String filePath = fileUtils.getLocalStorage() + url;
                File newFile = new File(filePath);
                file.transferTo(newFile);
            }
            save(document);
            return document;
        } catch (IllegalStateException | IOException e) {
            logger.error("保存文件失败", e);
            throw new BizfwServiceException(ErrorCode.Doc.File.SAVE_FILE_ERROR, e);
        }
    }

    @Transactional
    @Override
    public String saveDocuments(List<MultipartFile> files, String peopleCode) throws BizfwServiceException {
        String documentGroupNo = StringUtils.generateUUID();
        for (MultipartFile file : files) {
            Document document = saveDocument(file, peopleCode);
            DocumentGroup documentGroup = new DocumentGroup(peopleCode, documentGroupNo, document.getIdBfDocument());
            documentGroupDao.save(documentGroup);
        }
        return documentGroupNo;
    }

    @Override
    public void modifyDocument(Document document) throws BizfwServiceException {
        Document dbDocument = queryById(document.getIdBfDocument());
        dbDocument.setName(document.getName());
        dbDocument.setExtension(FileUtils.getExtension(document.getName()));
        dbDocument.update(document.getUpdateBy());
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
        DocumentChain documentChain = documentChainDao.getLatestChain(documentId);
        DocumentChain newChain = new DocumentChain(newDocument.getUpdateBy());
        newChain.setChainNo(documentChain.getChainNo());
        newChain.setDocumentId(newDocument.getIdBfDocument());
        if (documentChain != null) {
            newChain.setRevision(documentChain.getRevision() + 1);
        } else {
            newChain.setRevision(DocumentChain.REVISION_FIRST);
        }
        documentChainDao.save(newChain);
    }

    @Override
    public Document getByMD5(String md5) throws BizfwServiceException {
        return queryUniqueByFieldAndValue(Document.COLUMN_MD5, md5);
    }

    @Override
    public BaseDao<Document> getBaseDao() {
        return documentDao;
    }

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @Autowired
    public void setDocumentChainDao(DocumentChainDao documentChainDao) {
        this.documentChainDao = documentChainDao;
    }

    @Autowired
    public void setDocumentGroupDao(DocumentGroupDao documentGroupDao) {
        this.documentGroupDao = documentGroupDao;
    }

    @Autowired
    public void setFileUtils(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }
}
