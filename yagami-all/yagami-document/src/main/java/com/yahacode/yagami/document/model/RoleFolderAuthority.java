package com.yahacode.yagami.document.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;

/**
 * the model of role and corresponding folders authority
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_role_folder_auth")
public class RoleFolderAuthority extends BaseModel {

    private static final long serialVersionUID = 3122928795008437565L;

    public static final String COLUMN_ROLE_ID = "roleId";

    public static final String COLUMN_FOLDER_ID = "folderId";

    public static final String COLUMN_AUTHORITY = "authority";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_role_folder_auth")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfRoleFolderAuth;

    /**
     * role pk
     */
    @Column(name = "id_bf_role")
    private String roleId;

    /**
     * folder pk
     */
    @Column(name = "id_bf_folder")
    private String folderId;

    /**
     * the authority of role and folder
     */
    @Column(name = "authority")
    private String authority;

    public RoleFolderAuthority() {
        super();
    }

    public RoleFolderAuthority(String peopleCode) {
        super(peopleCode);
    }

    public RoleFolderAuthority(String peopleCode, String roleId, String folderId) {
        super(peopleCode);
        this.roleId = roleId;
        this.folderId = folderId;
    }

    public String getIdBfRoleFolderAuth() {
        return idBfRoleFolderAuth;
    }

    public void setIdBfRoleFolderAuth(String idBfRoleFolderAuth) {
        this.idBfRoleFolderAuth = idBfRoleFolderAuth;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}