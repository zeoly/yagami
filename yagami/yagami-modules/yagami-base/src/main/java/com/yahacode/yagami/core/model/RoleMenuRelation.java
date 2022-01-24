//package com.yahacode.yagami.core.model;
//
//import com.yahacode.yagami.base.BaseModel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
///**
// * 角色菜单关联模型
// *
// * @author zengyongli
// */
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Entity
//@Table(name = "bf_role_menu_rel")
//public class RoleMenuRelation extends BaseModel {
//
//    private static final long serialVersionUID = -4727978116878032279L;
//
//    public static final String COLUMN_ROLE_ID = "roleId";
//
//    public static final String COLUMN_MENU_ID = "menuId";
//
//    /**
//     * 主键
//     */
//    @Id
//    @Column(name = "id_bf_role_menu_rel")
//    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
//    @GeneratedValue(generator = "uuid")
//    private String idBfRoleMenuRel;
//
//    /**
//     * 角色id
//     */
//    @Column(name = "id_bf_role")
//    private String roleId;
//
//    /**
//     * 菜单id
//     */
//    @Column(name = "id_bf_menu")
//    private String menuId;
//
//    @Override
//    public String getId() {
//        return idBfRoleMenuRel;
//    }
//}
