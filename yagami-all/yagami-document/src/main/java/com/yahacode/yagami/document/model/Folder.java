package com.yahacode.yagami.document.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;

/**
 * the model for folder which contain several documents
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_folder")
public class Folder extends BaseModel {

    private static final long serialVersionUID = -1240258850763689533L;

    public static final String ROOT_NAME = "root";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_PARENT_ID = "parentId";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_folder")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfFolder;

    /**
     * folder name
     */
    @Column(name = "name")
    private String name;

    /**
     * the parent folder's pk
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * the child folders
     */
    @Transient
    private List<Folder> childFolderList;

    /**
     * the child documents
     */
    @Transient
    private List<Document> documentList;

    public Folder(String peopleCode) {
        super(peopleCode);
    }

    public String getIdBfFolder() {
        return idBfFolder;
    }

    public void setIdBfFolder(String idBfFolder) {
        this.idBfFolder = idBfFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Folder> getChildFolderList() {
        return childFolderList;
    }

    public void setChildFolderList(List<Folder> childFolderList) {
        this.childFolderList = childFolderList;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setFileList(List<Document> documentList) {
        this.documentList = documentList;
    }
}
