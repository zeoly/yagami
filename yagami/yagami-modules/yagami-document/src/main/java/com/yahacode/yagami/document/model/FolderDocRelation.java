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
 * the model for the relation of folder and its child documents
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "bf_folder_doc_rel")
public class FolderDocRelation extends BaseModel {

    public static final String COLUMN_FOLDER_ID = "folderId";

    public static final String COLUMN_DOCUMENT_ID = "documentId";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_folder_doc_rel")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfFolderDocRel;

    /**
     * folder pk
     */
    @Column(name = "id_bf_folder")
    private String folderId;

    /**
     * document pk
     */
    @Column(name = "id_bf_document")
    private String documentId;

    public FolderDocRelation(String peopleCode) {
        super(peopleCode);
    }

    @Override
    public String getId() {
        return idBfFolderDocRel;
    }

    public FolderDocRelation(String peopleCode, String folderId, String documentId) {
        super(peopleCode);
        this.folderId = folderId;
        this.documentId = documentId;
    }

}
