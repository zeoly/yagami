package com.yahacode.yagami.document.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;

/**
 * document service
 *
 * @author zengyongli
 */
public interface DocumentService extends BaseService<Document> {

    /**
     * add a new document
     *
     * @param document
     *         target document
     * @return document pk
     * @throws BizfwServiceException
     *         framework exception
     */
    String addDocument(Document document) throws BizfwServiceException;

    /**
     * modify document's name and extension
     *
     * @param document
     *         target document
     * @throws BizfwServiceException
     *         framework exception
     */
    void modifyDocument(Document document) throws BizfwServiceException;

    /**
     * increase the download count of document
     *
     * @param document
     *         target document
     * @throws BizfwServiceException
     *         framework exception
     */
    void downloadDocument(Document document) throws BizfwServiceException;

    /**
     * delete document
     *
     * @param document
     *         target document
     * @throws BizfwServiceException
     *         framework exception
     */
    void deleteDocument(Document document) throws BizfwServiceException;

}
