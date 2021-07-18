package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
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
 * the model for a group of documents
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "bf_doc_group")
public class DocumentGroup extends BaseModel {

    public static final String COLUMN_GROUP_NO = "groupNo";

    public static final String COLUMN_DOCUMENT_ID = "documentId";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_doc_group")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDocumentGroup;

    /**
     * the group number, documents in a group have the same group number
     */
    @Column(name = "group_no")
    private String groupNo;

    /**
     * document pk
     */
    @Column(name = "id_bf_document")
    private String documentId;

    @Override
    public String getId() {
        return idBfDocumentGroup;
    }

    public DocumentGroup(String peopleCode, String groupNo, String documentId) {
        super(peopleCode);
        this.groupNo = groupNo;
        this.documentId = documentId;
    }

}
