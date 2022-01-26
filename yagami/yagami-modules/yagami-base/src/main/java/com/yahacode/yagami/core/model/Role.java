package com.yahacode.yagami.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.consts.SystemConstants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * model of role
 *
 * @author zengyongli
 */
@Entity
@Table(name = SystemConstants.TABLE_PREFIX + "role")
@GenericGenerator(name = "my_uuid", strategy = "uuid")
public class Role extends BaseModel {

    private static final long serialVersionUID = -8526551139465233349L;

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "my_uuid")
    private String id;

    /**
     * role name
     */
    private String name;

    /**
     * description of role
     */
    @Column(name = "`desc`")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roleList")
    private List<Person> personList = new ArrayList<>();

    @Override
    public String getId() {
        return id;
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

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
