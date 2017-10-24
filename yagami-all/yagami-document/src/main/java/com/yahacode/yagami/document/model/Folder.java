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
 * 文件夹模型
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
     * 主键
     */
    @Id
    @Column(name = "id_bf_folder")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfFolder;

    /**
     * 文件夹名
     */
    @Column(name = "name")
    private String name;

    /**
     * 路径
     */
    @Column(name = "path")
    private String path;

    /**
     * 父文件夹id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 子文件夹列表
     */
    @Transient
    private List<Folder> childFolderList;

    /**
     * 子文件列表
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
