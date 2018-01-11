package com.yahacode.yagami.document.dao;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.document.model.DocumentChain;

/**
 * @author zengyongli
 */
public interface DocumentChainDao extends BaseDao<DocumentChain> {

    /**
     * get the latest chain relationship of a document
     *
     * @param documentId
     *         document pl
     * @return latest chain
     */
    DocumentChain getLatestChain(String documentId);

}
