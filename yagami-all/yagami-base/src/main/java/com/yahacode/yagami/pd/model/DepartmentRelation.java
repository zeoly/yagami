package com.yahacode.yagami.pd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;

/**
 * model of department relation, including cross level
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bf_department_rel")
public class DepartmentRelation extends BaseModel {

    private static final long serialVersionUID = -4315263369289046960L;

    public static final String COLUMN_PARENT_DEPARTMENT_ID = "parentDepartmentId";

    public static final String COLUMN_CHILD_DEPARTMENT_ID = "childDepartmentId";

    public static final String COLUMN_PARENT_LEVEL = "parentLevel";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_department_rel")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDepartmentRel;

    /**
     * parent department pk
     */
    @Column(name = "id_parent_department")
    private String parentDepartmentId;

    /**
     * child department pk
     */
    @Column(name = "id_child_department")
    private String childDepartmentId;

    /**
     * parent department level
     */
    @Column(name = "parent_level")
    private int parentLevel;

    public DepartmentRelation(String peopleCode) {
        super(peopleCode);
    }

}
