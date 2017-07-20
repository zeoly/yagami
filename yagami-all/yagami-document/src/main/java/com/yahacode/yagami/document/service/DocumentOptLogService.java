package com.yahacode.yagami.document.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.DocumentOperationLog;

/**
 * 文档操作日志服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月19日
 */
public interface DocumentOptLogService extends BaseService<DocumentOperationLog> {

	/**
	 * 新增文档日志
	 * 
	 * @param document
	 *            操作文档
	 * @param operation
	 *            操作类型
	 */
	public void addLog(Document document, String operation);
}
