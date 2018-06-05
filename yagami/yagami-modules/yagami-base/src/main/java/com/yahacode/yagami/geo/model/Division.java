package com.yahacode.yagami.geo.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.AllArgsConstructor;
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
 * @author zengyongli 2018-05-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bf_division")
public class Division extends BaseModel {

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_division")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDivision;

    private String name;

    private String code;

    private String parentCode;

    private int level;

    @Override
    public String getId() {
        return idBfDivision;
    }
}
