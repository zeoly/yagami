package com.yahacode.yagami.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;

/**
 * model of role
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_role")
public class Role extends BaseModel {

    private static final long serialVersionUID = -8526551139465233349L;

    public static final String COLUMN_NAME = "name";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_role")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfRole;

    /**
     * role name
     */
    @Column(name = "name")
    private String name;

    /**
     * description of role
     */
    @Column(name = "description")
    private String description;

    public Role(String peopleCode, String name, String desc) {
        super(peopleCode);
        this.name = name;
        this.description = desc;
    }

    public Role() {
        super();
    }

    public String getIdBfRole() {
        return idBfRole;
    }

    public void setIdBfRole(String idBfRole) {
        this.idBfRole = idBfRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
