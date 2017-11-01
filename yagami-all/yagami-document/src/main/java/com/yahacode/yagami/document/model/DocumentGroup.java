package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
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
@Entity
@Table(name = "bf_doc_group")
public class DocumentGroup extends BaseModel {

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_doc_group")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDocumentGroup;

    /**
     * the group number, doucuments in a group have the same group number
     */
    @Column(name = "group_no")
    private String groupNo;

    /**
     * document pk
     */
    @Column(name = "id_bf_document")
    private String documentId;

    public DocumentGroup() {
    }

    public DocumentGroup(String peopleCode) {
        super(peopleCode);
    }

    public String getIdBfDocumentGroup() {
        return idBfDocumentGroup;
    }

    public void setIdBfDocumentGroup(String idBfDocumentGroup) {
        this.idBfDocumentGroup = idBfDocumentGroup;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
