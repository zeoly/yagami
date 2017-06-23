package com.thinkequip.anochat.base;

import java.io.Serializable;
import java.util.List;

/**
 * 框架基础服务接口
 * 
 * @copyright THINKEQUIP
 * @author zengyongli
 * @date 2017年3月18日
 * @param <T>
 *            维护对象
 */
public interface BaseService<T extends Serializable> {

	/**
	 * 获取所有记录
	 * 
	 * @return 记录列表
	 */
	public List<T> list();

	/**
	 * 保存操作
	 * 
	 * @param t
	 *            维护对象
	 * @return 主键
	 */
	public String save(T t);

	/**
	 * 根据主键删除记录
	 * 
	 * @param id
	 *            记录主键
	 */
	public void delete(String id);

	/**
	 * 根据主键更新记录
	 * 
	 * @param t
	 *            维护对象
	 */
	public void update(T t);

	/**
	 * 根据主键查询记录
	 * 
	 * @param id
	 *            主键
	 * @return 维护对象
	 */
	public T queryById(String id);

	/**
	 * 条件查询，使用“等于”条件匹配
	 * 
	 * @param field
	 *            字段名
	 * @param value
	 *            查询值
	 * @return 维护对象
	 */
	public List<T> queryByFieldAndValue(String field, Object value);

	/**
	 * 获取对象对应dao
	 * 
	 * @return 对应对象dao
	 */
	public BaseDao<T> getBaseDao();
}
