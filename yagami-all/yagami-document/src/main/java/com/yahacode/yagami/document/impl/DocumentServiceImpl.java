package com.yahacode.yagami.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.utils.FileUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.dao.DocumentDao;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.service.DocumentService;

/**
 * the implementation of DocumentService
 *
 * @author zengyongli
 */
@Service
public class DocumentServiceImpl extends BaseServiceImpl<Document> implements DocumentService {

    private DocumentDao documentDao;

    @Override
    public String addDocument(Document document) throws BizfwServiceException {
        String ext = FileUtils.getExtension(document.getName());
        document.setDownloadCount(0);
        document.setExtension(ext);
        return save(document);
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
        delete(document.getIdBfDocument());
    }

    @Override
    public BaseDao<Document> getBaseDao() {
        return documentDao;
    }

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
}
