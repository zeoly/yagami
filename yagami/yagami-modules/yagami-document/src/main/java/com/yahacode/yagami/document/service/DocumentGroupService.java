package com.yahacode.yagami.document.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentGroup;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zengyongli
 */
public interface DocumentGroupService extends BaseService<DocumentGroup> {

    /**
     * add document to a group
     *
     * @param file
     *         target file
     * @param groupNo
     *         document group pk
     * @return document pk
     * @throws BizfwServiceException
     *         framework exception
     */
    String addDocument(MultipartFile file, String groupNo) throws BizfwServiceException;

    /**
     * get the documents of the same group number
     *
     * @param groupNo
     *         document group number
     * @return document list
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Document> getByGroupNo(String groupNo) throws BizfwServiceException;
}
