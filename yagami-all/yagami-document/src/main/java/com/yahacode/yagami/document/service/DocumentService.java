package com.yahacode.yagami.document.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * save the file and return the document
     *
     * @param file
     *         target file
     * @param peopleCode
     *         operator code
     * @return document entity
     * @throws BizfwServiceException
     *         framework exception
     */
    Document saveDocument(MultipartFile file, String peopleCode) throws BizfwServiceException;

    /**
     * save batch files
     *
     * @param files
     *         target files
     * @param peopleCode
     *         operator code
     * @return list of documents
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Document> saveDocuments(List<MultipartFile> files, String peopleCode) throws BizfwServiceException;

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

    /**
     * update document version
     *
     * @param newDocument
     *         new document
     * @param documentId
     *         old document pk
     * @throws BizfwServiceException
     *         framework exception
     */
    void updateDocument(Document newDocument, String documentId) throws BizfwServiceException;

}
