package com.yahacode.yagami.document.impl;

import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.DocumentDao;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentGroup;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * the implementation of DocumentDao
 *
 * @author zengyongli
 */
@Repository
public class DocumentDaoImpl extends BaseDaoImpl<Document> implements DocumentDao {

    @Override
    public List<Document> getByGroupNo(String groupNo) {
        String hql = "from " + getTableName() + " where " + Document.COLUMN_ID + " in (select b." +
                DocumentGroup.COLUMN_DOCUMENT_ID + " from DocumentGroup b where b." + DocumentGroup.COLUMN_GROUP_NO +
                "=" + " :" + DocumentGroup.COLUMN_GROUP_NO + ")";
        Query query = createQuery(hql);
        query.setParameter(DocumentGroup.COLUMN_GROUP_NO, groupNo);
        return query.list();
    }
}
