package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.document.model.DocumentChain;

/**
 * @author zengyongli 2018-04-02
 */
public interface DocumentChainRepository extends BaseRepository<DocumentChain, String> {

    DocumentChain findByDocumentId(String documentId);
}
