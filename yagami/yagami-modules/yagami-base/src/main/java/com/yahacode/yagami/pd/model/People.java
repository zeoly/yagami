package com.yahacode.yagami.pd.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;

/**
 * model of people, classified by Role
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "bf_people")
public class People extends BaseModel {

    private static final long serialVersionUID = -9173667783512729829L;

    public static final String COLUMN_CODE = "code";

    public static final String COLUMN_DEPARTMENT_ID = "departmentId";

    public static final String STATUS_INVALID = "0";

    public static final String STATUS_NORMAL = "1";

    public static final String STATUS_LOCKED = "2";

    public static final String STATUS_UNCHECK = "3";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_people")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfPeople;

    /**
     * people code
     */
    @Column(name = "code")
    private String code;

    /**
     * people name, recommend in Chinese
     */
    @Column(name = "name")
    private String name;

    /**
     * people login password, storage in md5
     */
    @Column(name = "password")
    private String password;

    /**
     * the people's department pk
     */
    @Column(name = "id_bf_department")
    private String departmentId;

    /**
     * people's account status, including STATUS_INVALID, STATUS_NORMAL, STATUS_LOCKED, STATUS_UNCHECK
     */
    @Column(name = "status")
    private String status;

    /**
     * login fail count, may cause account locked
     */
    @Column(name = "error_count")
    private int errorCount;

    /**
     * role list of a people
     */
    @Transient
    private List<String> roleIdList;

    public People(String peopleCode) {
        super(peopleCode);
        this.errorCount = 0;
        this.status = STATUS_NORMAL;
        this.password = StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd"));
    }

    @Override
    public String getId() {
        return idBfPeople;
    }

}
