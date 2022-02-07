package com.yahacode.yagami.core.model;

import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.consts.SystemConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * model of a department or a division
 *
 * @author zengyongli
 */
@Entity
@Table(name = SystemConstants.TABLE_PREFIX + "department")
@GenericGenerator(name = "my_uuid", strategy = "uuid")
public class Department extends BaseModel {

    private static final long serialVersionUID = -8679405946033789045L;

    public static final int LEVEL_ROOT = 0;

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "my_uuid")
    private String id;

    /**
     * department code
     */
    @Column(unique = true)
    private String code;

    /**
     * department name, recommend in Chinese
     */
    private String name;

    /**
     * department level, the level of root department is 0, the offspring's level increase 1
     */
    private Integer level;

    /**
     * parent department code
     */
    @Column(name = "parent_code")
    private String parentCode;

    @Override
    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}
