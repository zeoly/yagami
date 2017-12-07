package com.yahacode.yagami.auth.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色菜单关联模型
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "bf_role_menu_rel")
public class RoleMenuRelation extends BaseModel {

    private static final long serialVersionUID = -4727978116878032279L;

    public static final String COLUMN_ROLE_ID = "roleId";

    public static final String COLUMN_MENU_ID = "menuId";

    /**
     * 主键
     */
    @Id
    @Column(name = "id_bf_role_menu_rel")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfRoleMenuRel;

    /**
     * 角色id
     */
    @Column(name = "id_bf_role")
    private String roleId;

    /**
     * 菜单id
     */
    @Column(name = "id_bf_menu")
    private String menuId;

    public RoleMenuRelation(String peopleCode, String roleId, String menuId) {
        super(peopleCode);
        this.roleId = roleId;
        this.menuId = menuId;
    }

}
