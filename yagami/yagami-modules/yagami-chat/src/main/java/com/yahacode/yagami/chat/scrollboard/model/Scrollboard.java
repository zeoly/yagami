package com.yahacode.yagami.chat.scrollboard.model;

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
 * @author zengyongli 2018-05-07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bf_scrollboard")
public class Scrollboard extends BaseModel {

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_scrollboard")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfScrollboard;

    /**
     * messager name
     */
    @Column(name = "name")
    private String name;

    /**
     * message
     */
    @Column(name = "message")
    private String message;

    @Override
    public String getId() {
        return idBfScrollboard;
    }
}
