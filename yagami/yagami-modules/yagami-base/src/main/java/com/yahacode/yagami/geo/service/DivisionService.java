package com.yahacode.yagami.geo.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.geo.model.Division;

import java.util.List;

/**
 * 行政区划服务
 *
 * @author zengyongli 2018-05-18
 */
public interface DivisionService extends BaseService<Division> {

    /**
     * 获取父级行政区
     *
     * @param code
     *         区码
     * @return 行政区
     */
    Division getParent(String code);

    /**
     * 获取子行政区列表
     *
     * @param code
     *         区码
     * @return 行政区列表
     */
    List<Division> getChidren(String code);
}
