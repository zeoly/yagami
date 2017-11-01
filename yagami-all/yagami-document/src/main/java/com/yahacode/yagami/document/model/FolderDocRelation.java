package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
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
@Entity
@Table(name = "bf_folder_file_rel")
public class FolderDocRelation extends BaseModel {

    public static final String COLUMN_FOLDER_ID = "folderId";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_folder_file_rel")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfFolderFileRel;

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


    public FolderDocRelation() {
    }

    public FolderDocRelation(String peopleCode) {
        super(peopleCode);
    }

    public FolderDocRelation(String peopleCode, String folderId, String documentId) {
        super(peopleCode);
        this.folderId = folderId;
        this.documentId = documentId;
    }

    public String getIdBfFolderFileRel() {
        return idBfFolderFileRel;
    }

    public void setIdBfFolderFileRel(String idBfFolderFileRel) {
        this.idBfFolderFileRel = idBfFolderFileRel;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

}
