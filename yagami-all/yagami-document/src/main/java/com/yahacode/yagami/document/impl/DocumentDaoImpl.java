package com.yahacode.yagami.document.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseDaoImpl;
import com.yahacode.yagami.document.dao.DocumentDao;
import com.yahacode.yagami.document.model.Document;

@Repository("documentDao")
public class DocumentDaoImpl extends BaseDaoImpl<Document> implements DocumentDao {

	@Override
	public long getChildCount(Document document) {
		long childCount = getCountByFieldAndValue(Document.COLUMN_OWNER_DOCUMENT_ID, document.getIdBfDocument());
		return childCount;
	}

	@Override
	public List<Document> getAllFolder() {
		List<Document> list = queryByFieldAndValue(Document.COLUMN_TYPE, Document.TYPE_DIRECTORY);
		return list;
	}

	@Override
	public Document getRootFolder() {
		List<Document> list = queryByFieldAndValue(Document.COLUMN_NAME, Document.ROOT_NAME);
		if (ListUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Document> getChildFolderList(Document document) {
		String hql = "from " + getTableName() + " where " + Document.COLUMN_TYPE + " = '" + Document.TYPE_DIRECTORY
				+ "' and " + Document.COLUMN_OWNER_DOCUMENT_ID + " = '" + document.getIdBfDocument() + "'";
		Query query = createQuery(hql);
		List<Document> list = query.list();
		return list;
	}

	@Override
	public List<Document> getContentOfFolder(Document document) {
		String hql = "from " + getTableName() + " where " + Document.COLUMN_OWNER_DOCUMENT_ID + "= :"
				+ Document.COLUMN_OWNER_DOCUMENT_ID + " order by " + Document.COLUMN_TYPE;
		Query query = createQuery(hql);
		query.setParameter(Document.COLUMN_OWNER_DOCUMENT_ID, document.getIdBfDocument());
		List<Document> list = query.list();
		return list;
	}

}
