package com.yahacode.yagami.pd.model;

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
import javax.persistence.Transient;
import java.util.List;

/**
 * model of department
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "bf_department")
public class Department extends BaseModel {

    private static final long serialVersionUID = -8679405946033789045L;

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_PARENT_DEPT_ID = "parentDepartmentId";

    public static final int LEVEL_ROOT = 0;

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_department")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDepartment;

    /**
     * department code
     */
    @Column(name = "code")
    private String code;

    /**
     * department name, recommend in Chinese
     */
    @Column(name = "name")
    private String name;

    /**
     * department level, the level of root department is 0, the offspring's level increase 1
     */
    @Column(name = "level")
    private Integer level;

    /**
     * parent department pk
     */
    @Column(name = "parent_dept_id")
    private String parentDepartmentId;

    /**
     * list of child department
     */
    @Transient
    private List<Department> childDepartmentList;

    @Override
    public String getId() {
        return idBfDepartment;
    }

}
