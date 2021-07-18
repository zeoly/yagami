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
 * model of the relation between people and role, one people can have relation with multiple roles
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "bf_people_role_rel")
public class PeopleRoleRelation extends BaseModel {

    private static final long serialVersionUID = -5402400327267119179L;

    public static final String COLUMN_PEOPLE_ID = "peopleId";

    public static final String COLUMN_ROLE_ID = "roleId";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_people_role_rel")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfPeopleRoleRel;

    /**
     * people pk
     */
    @Column(name = "id_bf_people")
    private String peopleId;

    /**
     * role pk
     */
    @Column(name = "id_bf_role")
    private String roleId;

    @Override
    public String getId() {
        return idBfPeopleRoleRel;
    }
}
