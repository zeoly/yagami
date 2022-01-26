package com.yahacode.yagami.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.SystemConstants;
import com.yahacode.yagami.core.util.PersonStatus;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * model of user or a natural person, classified by Role, owned to a department
 *
 * @author zengyongli
 */
@Entity
@DynamicUpdate
@Table(name = SystemConstants.TABLE_PREFIX + "person")
@GenericGenerator(name = "my_uuid", strategy = "uuid")
public class Person extends BaseModel {

    private static final long serialVersionUID = -9173667783512729829L;

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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code", referencedColumnName = "code", unique = true, insertable = false, updatable = false, nullable = false)
    private Department department;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = SystemConstants.TABLE_PREFIX + "person_role_rel",
            joinColumns = @JoinColumn(name = "person_code", referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList = new ArrayList<>();

    /**
     * login password, storage in md5
     */
    @Basic(fetch = FetchType.LAZY)
    private String password;

    /**
     * person's account status
     */
    @Enumerated
    private PersonStatus status;

    /**
     * login fail count, may cause account locked
     */
    @Column(name = "error_count")
    private int errorCount;

    public Person() {
    }

    /**
     * construct a new person object
     *
     * @param code person code
     */
    public Person(String code) {
        super(code);
        this.errorCount = 0;
        this.status = PersonStatus.NORMAL;
        this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
    }

    @Override
    public void init(String personCode) {
        super.init(personCode);
        this.errorCount = 0;
        this.status = PersonStatus.NORMAL;
        this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
    }

    public void unlock() {
        this.setStatus(PersonStatus.NORMAL);
        this.setErrorCount(0);
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonStatus getStatus() {
        return status;
    }

    public void setStatus(PersonStatus status) {
        this.status = status;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }
}
