package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * the model of role and corresponding folders authority
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
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

    @Override
    public String getId() {
        return idBfRoleFolderAuth;
    }

    public RoleFolderAuthority(String peopleCode, String roleId, String folderId) {
        super(peopleCode);
        this.roleId = roleId;
        this.folderId = folderId;
    }

}
