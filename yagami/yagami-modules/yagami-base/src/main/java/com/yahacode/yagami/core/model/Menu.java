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
 * model of system menu, relation to roles
 *
 * @author zengyongli
 * @since 2017/03/19
 */
@Entity
@Table(name = SystemConstants.TABLE_PREFIX + "menu")
@GenericGenerator(name = "my_uuid", strategy = "uuid")
public class Menu extends BaseModel {

    private static final long serialVersionUID = 8163772024398615538L;

    /**
     * primary key
     */
    @Id
    @GeneratedValue(generator = "uuid")
    private String id;

    /**
     * menu name
     */
    private String name;

    /**
     * corresponding url
     */
    private String url;

    /**
     * menu sort order, starting from 1, the invisible root menu is 0
     */
    private Integer orders;

    /**
     * parent menu id, the level 1 menu's parent id is null;
     */
    @Column(name = "parent_id")
    private String parentId;

    public Menu(String peopleCode, String name, String url, Integer orders, String parentId) {
        super(peopleCode);
        this.name = name;
        this.url = url;
        this.orders = orders;
        this.parentId = parentId;
    }

    public Menu() {
        super();
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
