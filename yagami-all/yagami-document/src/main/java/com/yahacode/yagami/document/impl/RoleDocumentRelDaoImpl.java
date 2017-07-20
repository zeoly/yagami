package com.yahacode.yagami.document.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.RoleDocumentRelDao;
import com.yahacode.yagami.document.model.RoleDocumentRelation;

@Repository("roleDocumentRelDao")
public class RoleDocumentRelDaoImpl extends BaseDaoImpl<RoleDocumentRelation> implements RoleDocumentRelDao {

	@Override
	public RoleDocumentRelation getRelation(String roleId, String documentId) {
		String hql = "from " + getTableName() + " where " + RoleDocumentRelation.COLUMN_ROLE_ID + " = :"
				+ RoleDocumentRelation.COLUMN_ROLE_ID + " and " + RoleDocumentRelation.COLUMN_DOCUMENT_ID + " = :"
				+ RoleDocumentRelation.COLUMN_DOCUMENT_ID;
		Query query = createQuery(hql);
		query.setParameter(RoleDocumentRelation.COLUMN_ROLE_ID, roleId);
		query.setParameter(RoleDocumentRelation.COLUMN_DOCUMENT_ID, documentId);
		List<RoleDocumentRelation> list = query.list();
		if (ListUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
