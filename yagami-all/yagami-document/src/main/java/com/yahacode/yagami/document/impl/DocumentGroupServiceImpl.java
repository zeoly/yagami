package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.document.dao.DocumentGroupDao;
import com.yahacode.yagami.document.model.DocumentGroup;
import com.yahacode.yagami.document.service.DocumentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zengyongli
 */
@Service
public class DocumentGroupServiceImpl extends BaseServiceImpl<DocumentGroup> implements DocumentGroupService {

    private DocumentGroupDao documentGroupDao;

    @Override
    public BaseDao<DocumentGroup> getBaseDao() {
        return documentGroupDao;
    }

    @Autowired
    public void setDocumentGroupDao(DocumentGroupDao documentGroupDao) {
        this.documentGroupDao = documentGroupDao;
    }
}
