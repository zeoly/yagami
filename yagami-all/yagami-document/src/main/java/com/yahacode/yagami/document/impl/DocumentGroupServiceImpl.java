package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.dao.DocumentGroupDao;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentGroup;
import com.yahacode.yagami.document.service.DocumentGroupService;
import com.yahacode.yagami.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zengyongli
 */
@Service
public class DocumentGroupServiceImpl extends BaseServiceImpl<DocumentGroup> implements DocumentGroupService {

    private DocumentGroupDao documentGroupDao;

    private DocumentService documentService;

    @Override
    public String addDocument(MultipartFile file, String groupNo, String peopleCode) throws BizfwServiceException {
        Document document = documentService.saveDocument(file, peopleCode);
        DocumentGroup documentGroup = new DocumentGroup(peopleCode, groupNo, document.getIdBfDocument());
        save(documentGroup);
        return document.getIdBfDocument();
    }

    @Override
    public BaseDao<DocumentGroup> getBaseDao() {
        return documentGroupDao;
    }

    @Autowired
    public void setDocumentGroupDao(DocumentGroupDao documentGroupDao) {
        this.documentGroupDao = documentGroupDao;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
