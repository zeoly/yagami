package com.yahacode.yagami.document.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import com.yahacode.yagami.base.BaseModel;

/**
 * the model for folder which contain several documents
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bf_folder")
public class Folder extends BaseModel {

    private static final long serialVersionUID = -1240258850763689533L;

    public static final String ROOT_NAME = "root";

    public static final String COLUMN_ID = "idBfFolder";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_PARENT_ID = "parentId";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_folder")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
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

    @Override
    public String getId() {
        return idBfFolder;
    }

}
