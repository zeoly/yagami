package com.yahacode.yagami.pd.model;

import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.SystemConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * model of user or a natural person, classified by Role, owned to a department
 *
 * @author zengyongli
 */
@Entity
@Table(name = SystemConstants.TABLE_PREFIX + "person")
@GenericGenerator(name = "my_uuid", strategy = "uuid")
public class Person extends BaseModel {

    private static final long serialVersionUID = -9173667783512729829L;

    public static final String STATUS_INVALID = "0";

    public static final String STATUS_NORMAL = "1";

    public static final String STATUS_LOCKED = "2";

    public static final String STATUS_UNCHECK = "3";

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "my_uuid")
    private String id;

    /**
     * person code
     */
    private String code;

    /**
     * person name, recommend in Chinese
     */
    private String name;

    /**
     * person’s department
     */
    private String departmentCode;

    /**
     * login password, storage in md5
     */
    private String password;

    /**
     * person's account status, including STATUS_INVALID, STATUS_NORMAL, STATUS_LOCKED, STATUS_UNCHECK
     */
    @Column(name = "status")
    private String status;

    /**
     * login fail count, may cause account locked
     */
    @Column(name = "error_count")
    private int errorCount;

    /**
     * construct a new person object
     *
     * @param code person code
     */
    public Person(String code) {
        super(code);
        this.errorCount = 0;
        this.status = STATUS_NORMAL;
        this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
    }

    @Override
    public void init(String personCode) {
        super.init(personCode);
        this.errorCount = 0;
        this.status = STATUS_NORMAL;
        this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
    }

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

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }
}
