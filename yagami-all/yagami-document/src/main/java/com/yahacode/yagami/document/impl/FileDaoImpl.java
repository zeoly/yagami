package com.yahacode.yagami.document.impl;

import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.FileDao;
import com.yahacode.yagami.document.model.Document;

/**
 * 文件dao实现类
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月21日
 */
@Repository("fileDao")
public class FileDaoImpl extends BaseDaoImpl<Document> implements FileDao {

}
