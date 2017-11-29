package com.yahacode.yagami.document.dao;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.document.model.Document;

import java.util.List;

/**
 * the DAO interface of Document
 *
 * @author zengyongli
 */
public interface DocumentDao extends BaseDao<Document> {

    /**
     * get the documents of the same group number
     *
     * @param groupNo
     *         document group number
     * @return document list
     */
    List<Document> getByGroupNo(String groupNo);
}
