package com.yahacode.yagami.document.model;

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
 * model of document history chain with version control function
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bf_document_chain")
public class DocumentChain extends BaseModel {

    public static final int REVISION_FIRST = 1;

    public static final String COLUMN_DOCUMENT_ID = "documentId";

    public static final String COLUMN_CHAIN_NO = "chainNo";

    public static final String COLUMN_REVISION = "revision";

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

    public DocumentChain(String peopleCode) {
        super(peopleCode);
    }

}
