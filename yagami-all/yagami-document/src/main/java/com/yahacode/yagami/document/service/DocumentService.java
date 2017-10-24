package com.yahacode.yagami.document.service;

import java.util.List;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.RoleDocumentRelation;
import com.yahacode.yagami.pd.model.People;

/**
 * 文档服务接口
 *
 * @author zengyongli
 */
public interface DocumentService extends BaseService<Document> {

    /**
     * 新增文档
     *
     * @param document
     *         文件
     * @return 主键
     * @throws BizfwServiceException
     *         业务异常
     */
    String addDocument(Document document) throws BizfwServiceException;

    /**
     * 修改文档（仅限于文件名与后缀名）
     *
     * @param document
     *         文档
     * @throws BizfwServiceException
     *         业务异常
     */
    void modifyDocument(Document document) throws BizfwServiceException;

    /**
     * 下载文档，更新下载次数
     *
     * @param document
     *         文档
     * @throws BizfwServiceException
     *         业务异常
     */
    void downloadDocument(Document document) throws BizfwServiceException;

    /**
     * 删除文档
     *
     * @param document
     *         文档
     * @throws BizfwServiceException
     *         业务异常
     */
    void deleteDocument(Document document) throws BizfwServiceException;

}
