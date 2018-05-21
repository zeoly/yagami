package com.yahacode.yagami.geo.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author zengyongli 2018-05-16
 */
@Data
@Builder
public class Division extends BaseModel {

    private String idBfDivision;

    private String name;

    private String code;

    private String parentCode;

    private int level;

    @Override
    public String getId() {
        return idBfDivision;
    }
}
