package com.yahacode.yagami.document.dao.impl;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.DocumentChainDao;
import com.yahacode.yagami.document.model.DocumentChain;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zengyongli
 */
@Deprecated
@Repository
public class DocumentChainDaoImpl extends BaseDaoImpl<DocumentChain> implements DocumentChainDao {

    @Override
    public DocumentChain getLatestChain(String documentId) {
        String hql = "select a from " + getTableName() + " a, " + getTableName() + "b where b." + DocumentChain
                .COLUMN_DOCUMENT_ID + "= :" + DocumentChain.COLUMN_DOCUMENT_ID + " and a." + DocumentChain
                .COLUMN_CHAIN_NO + " = b." + DocumentChain.COLUMN_CHAIN_NO + " order by a." + DocumentChain
                .COLUMN_REVISION + " desc";
        Query query = createQuery(hql);
        query.setParameter(DocumentChain.COLUMN_DOCUMENT_ID, documentId);
        List<DocumentChain> list = query.list();
        if (ListUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
