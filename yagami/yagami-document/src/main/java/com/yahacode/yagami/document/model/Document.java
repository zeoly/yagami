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
 * the model of document for all format
 *
 * @author zengyongli
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bf_document")
public class Document extends BaseModel {

    private static final long serialVersionUID = -7390005208539497792L;

    public static final int REVISION_FIRST = 1;

    public static final String COLUMN_ID = "idBfDocument";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_REVISION = "revision";

    public static final String COLUMN_MD5 = "md5";

    public static final String STATUS_DELETED = "0";

    public static final String STATUS_NORMAL = "1";

    /**
     * primary key
     */
    @Id
    @Column(name = "id_bf_document")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String idBfDocument;

    /**
     * docuemnt name
     */
    @Column(name = "name")
    private String name;

    /**
     * file extension
     */
    @Column(name = "extension")
    private String extension;

    /**
     * document relative storage path, for downloading
     */
    @Column(name = "url")
    private String url;

    /**
     * the document size in byte
     */
    @Column(name = "size")
    private Long size;

    /**
     * memo
     */
    @Column(name = "memo")
    private String memo;

    /**
     * document download counter
     */
    @Column(name = "download_count")
    private Integer downloadCount;

    /**
     * the md5 of document for avoiding redundancy
     */
    @Column(name = "md5")
    private String md5;

    /**
     * the status of document
     */
    private String status;

    public Document(String peopleCode) {
        super(peopleCode);
    }

}
