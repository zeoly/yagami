package com.yahacode.yagami.document.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.DocumentGroup;
import org.springframework.web.multipart.MultipartFile;

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
     * @param peopleCode
     *         operator code
     * @return document pk
     * @throws BizfwServiceException
     *         framework exception
     */
    String addDocument(MultipartFile file, String groupNo, String peopleCode) throws BizfwServiceException;
}
