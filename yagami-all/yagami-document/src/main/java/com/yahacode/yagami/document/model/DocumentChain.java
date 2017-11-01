package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * model of document history chain with version control function
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_document_chain")
public class DocumentChain extends BaseModel {


    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_document_chain")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDocumentChain;

    /**
     * document chain number, documents with the same chainNo represent different version
     */
    @Column(name = "chain_no")
    private String chainNo;

    /**
     * document pk
     */
    @Column(name = "idBfDocument")
    private String documentId;

    /**
     * version number, start from 1
     */
    @Column(name = "revision")
    private int revision;

    /**
     * revision memo
     */
    @Column(name = "memo")
    private String memo;

    public DocumentChain() {
    }

    public String getIdBfDocumentChain() {
        return idBfDocumentChain;
    }

    public void setIdBfDocumentChain(String idBfDocumentChain) {
        this.idBfDocumentChain = idBfDocumentChain;
    }

    public String getChainNo() {
        return chainNo;
    }

    public void setChainNo(String chainNo) {
        this.chainNo = chainNo;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
