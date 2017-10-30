package com.yahacode.yagami.pd.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;

/**
 * model of department
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_department")
public class Department extends BaseModel {

    private static final long serialVersionUID = -8679405946033789045L;

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_PARENT_DEPT_ID = "parentDepartmentId";

    public static final int LEVEL_ROOT = 0;

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_department")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfDepartment;

    /**
     * department code
     */
    @Column(name = "code")
    private String code;

    /**
     * department name, recommend in Chinese
     */
    @Column(name = "name")
    private String name;

    /**
     * department level, the level of root department is 0, the offspring's level increase 1
     */
    @Column(name = "level")
    private Integer level;

    /**
     * parent department pk
     */
    @Column(name = "parent_dept_id")
    private String parentDepartmentId;

    /**
     * list of child department
     */
    @Transient
    private List<Department> childDepartmentList;

    public Department() {
        super();
    }

    public Department(String peopleCode) {
        super(peopleCode);
    }

    public String getIdBfDepartment() {
        return idBfDepartment;
    }

    public void setIdBfDepartment(String idBfDepartment) {
        this.idBfDepartment = idBfDepartment;
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

    public String getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public List<Department> getChildDepartmentList() {
        return childDepartmentList;
    }

    public void setChildDepartmentList(List<Department> childDepartmentList) {
        this.childDepartmentList = childDepartmentList;
    }
}
