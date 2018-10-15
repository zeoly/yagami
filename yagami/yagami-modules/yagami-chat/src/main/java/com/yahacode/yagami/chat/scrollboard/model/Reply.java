package com.yahacode.yagami.chat.scrollboard.model;

import com.yahacode.yagami.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zengyongli 2018-09-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bf_reply")
public class Reply extends BaseModel {

    @Id
    @Column(name = "id_bf_reply")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfReply;

    @Column(name = "id_bf_post")
    private String postId;

    @Column(name = "content")
    private String content;

    @Override
    public String getId() {
        return this.idBfReply;
    }
}
