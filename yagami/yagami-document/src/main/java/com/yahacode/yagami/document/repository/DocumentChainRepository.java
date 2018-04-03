package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.document.model.DocumentChain;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zengyongli 2018-04-02
 */
public interface DocumentChainRepository extends JpaRepository<DocumentChain, String> {

    DocumentChain findByDocumentId(String documentId);
}
