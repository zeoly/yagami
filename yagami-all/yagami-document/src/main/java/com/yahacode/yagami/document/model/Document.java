package com.yahacode.yagami.document.model;

import com.yahacode.yagami.base.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文档模型
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

    /**
     * 主键
     */
    @Id
    @Column(name = "id_bf_document")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String idBfDocument;

    /**
     * 文件名
     */
    @Column(name = "name")
    private String name;

    /**
     * 扩展名
     */
    @Column(name = "extension")
    private String extension;

    /**
     * 文件物理存储url，下载用
     */
    @Column(name = "url")
    private String url;

    /**
     * 文件大小，单位byte
     */
    @Column(name = "size")
    private Long size;

    /**
     * 备注
     */
    @Column(name = "memo")
    private String memo;

    /**
     * 下载次数
     */
    @Column(name = "download_count")
    private Integer downloadCount;

    /**
     * 文档对应md5
     */
    @Column(name = "md5")
    private String md5;

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

}
