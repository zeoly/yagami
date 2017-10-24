package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文件夹与文件关联关系模型
 *
 * @author zengyongli
 */
@Entity
@Table(name = "bf_folder_file_rel")
public class FolderDocRelation extends BaseModel {

    public static final String COLUMN_FOLDER_ID = "folderId";

    /**
     * 主键
     */
    @Id
    @Column(name = "id_bf_folder_file_rel")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfFolderFileRel;

    /**
     * 文件夹id
     */
    @Column(name = "id_bf_folder")
    private String folderId;

    /**
     * 文件id
     */
    @Column(name = "id_bf_file")
    private String fileId;


    public FolderDocRelation() {
    }

    public FolderDocRelation(String peopleCode) {
        super(peopleCode);
    }

    public FolderDocRelation(String peopleCode, String folderId, String fileId) {
        super(peopleCode);
        this.folderId = folderId;
        this.fileId = fileId;
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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

}
