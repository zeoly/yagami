package com.yahacode.yagami.document.impl;

import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.DocumentOptLogDao;
import com.yahacode.yagami.document.model.DocumentOperationLog;

@Repository("documentOptLogDao")
public class DocumentOptLogDaoImpl extends BaseDaoImpl<DocumentOperationLog> implements DocumentOptLogDao {

}
