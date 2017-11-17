package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
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
@Entity
@Table(name = "bf_document")
public class Document extends BaseModel {

    private static final long serialVersionUID = -7390005208539497792L;

    public static final int REVISION_FIRST = 1;

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

    public Document() {
    }

    public Document(String peopleCode) {
        super(peopleCode);
    }

    public String getIdBfDocument() {
        return idBfDocument;
    }

    public void setIdBfDocument(String idBfDocument) {
        this.idBfDocument = idBfDocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
