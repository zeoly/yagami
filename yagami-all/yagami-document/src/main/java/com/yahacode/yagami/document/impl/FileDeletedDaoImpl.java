package com.yahacode.yagami.document.impl;

import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.FileDeletedDao;
import com.yahacode.yagami.document.model.FileDeleted;

@Repository("fileDeletedDao")
public class FileDeletedDaoImpl extends BaseDaoImpl<FileDeleted> implements FileDeletedDao {

}