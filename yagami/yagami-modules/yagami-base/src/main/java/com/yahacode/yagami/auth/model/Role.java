package com.yahacode.yagami.auth.model;

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
 * model of role
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
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
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
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

    @Override
    public String getId() {
        return idBfRole;
    }
}
