package com.yahacode.yagami.document.service.impl;

import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentGroup;
import com.yahacode.yagami.document.repository.DocumentGroupRepository;
import com.yahacode.yagami.document.repository.DocumentRepository;
import com.yahacode.yagami.document.service.DocumentGroupService;
import com.yahacode.yagami.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zengyongli
 */
@Service
public class DocumentGroupServiceImpl extends BaseServiceImpl<DocumentGroup> implements DocumentGroupService {

    private DocumentGroupRepository documentGroupRepository;

    private DocumentRepository documentRepository;

    private DocumentService documentService;

    @Override
    public String addDocument(MultipartFile file, String groupNo) throws BizfwServiceException {
        Document document = documentService.saveDocument(file);
        DocumentGroup documentGroup = new DocumentGroup(getLoginPeople().getCode(), groupNo, document.getIdBfDocument
                ());
        save(documentGroup);
        return document.getIdBfDocument();
    }

    @Override
    public List<Document> getByGroupNo(String groupNo) throws BizfwServiceException {
        return documentRepository.findAllByGroupNo(groupNo);
    }

    @Autowired
    public void setDocumentGroupRepository(DocumentGroupRepository documentGroupRepository) {
        this.documentGroupRepository = documentGroupRepository;
    }

    @Autowired
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public JpaRepository<DocumentGroup, String> getBaseRepository() {
        return null;
    }
}
