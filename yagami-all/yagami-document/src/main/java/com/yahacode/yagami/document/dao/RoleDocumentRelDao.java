package com.yahacode.yagami.document.dao;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.document.model.RoleDocumentRelation;

public interface RoleDocumentRelDao extends BaseDao<RoleDocumentRelation> {

	public RoleDocumentRelation getRelation(String roleId, String documentId);
}
