package com.yahacode.yagami.chat.scrollboard.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zengyongli 2018-09-16
 */
@Data
@Entity
@Table(name = "bf_post")
public class Post extends BaseModel {

    @Id
    @Column(name = "id_bf_post")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfPost;

    @Column(name = "title")
    private String title;

    @Column
    private String content;

    @Override
    public String getId() {
        return this.idBfPost;
    }
}
