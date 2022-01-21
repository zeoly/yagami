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
 * model of the relation between person and role, a person can have relation with multiple roles
 *
 * @author zengyongli
 */
@Entity
@Table(name = SystemConstants.TABLE_PREFIX + "person_role_rel")
@GenericGenerator(name = "my_uuid", strategy = "uuid")
public class PersonRoleRelation extends BaseModel {

    private static final long serialVersionUID = -5402400327267119179L;

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "my_uuid")
    private String id;

    /**
     * person code
     */
    @Column(name = "person_code")
    private String personCode;

    /**
     * role primary key
     */
    @Column(name = "role_id")
    private String roleId;

    public PersonRoleRelation(String operatorCode, String personCode, String roleId) {
        super(operatorCode);
        this.personCode = personCode;
        this.roleId = roleId;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
