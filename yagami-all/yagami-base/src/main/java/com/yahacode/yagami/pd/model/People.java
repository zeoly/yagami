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
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;

/**
 * model of people, classified by Role
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_people")
public class People extends BaseModel {

    private static final long serialVersionUID = -9173667783512729829L;

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_DEPARTMENT_ID = "departmentId";

    public static final String STATUS_INVALID = "0";

    public static final String STATUS_NORMAL = "1";

    public static final String STATUS_LOCKED = "2";

    public static final String STATUS_UNCHECK = "3";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_people")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfPeople;

    /**
     * people code
     */
    @Column(name = "code")
    private String code;

    /**
     * people name, recommend in Chinese
     */
    @Column(name = "name")
    private String name;

    /**
     * people login password, storage in md5
     */
    @Column(name = "password")
    private String password;

    /**
     * the people's department pk
     */
    @Column(name = "id_bf_department")
    private String departmentId;

    /**
     * people's account status, including STATUS_INVALID, STATUS_NORMAL, STATUS_LOCKED, STATUS_UNCHECK
     */
    @Column(name = "status")
    private String status;

    /**
     * login fail count, may cause account locked
     */
    @Column(name = "error_count")
    private int errorCount;

    /**
     * role list of a people
     */
    @Transient
    private List<String> roleIdList;

    public People() {
        super();
    }

    public People(String peopleCode) {
        super(peopleCode);
        this.errorCount = 0;
        this.status = STATUS_NORMAL;
        this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
    }

    public String getIdBfPeople() {
        return idBfPeople;
    }

    public void setIdBfPeople(String idBfPeople) {
        this.idBfPeople = idBfPeople;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

}
