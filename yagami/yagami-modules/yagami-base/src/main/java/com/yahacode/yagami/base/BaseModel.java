package com.yahacode.yagami.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base entity model, recommend to extend this to build your own business model
 *
 * @author zengyongli
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 2305562441218971838L;

    /**
     * create date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    /**
     * create person code
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * update date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     * update person code
     */
    @Column(name = "update_by")
    private String updateBy;

    public BaseModel(String personCode) {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.createBy = personCode;
        this.updateBy = personCode;
    }

    public BaseModel() {
    }

    public void init(String personCode) {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.createBy = personCode;
        this.updateBy = personCode;
    }

    public void update(String personCode) {
        this.updateDate = LocalDateTime.now();
        this.updateBy = personCode;
    }

    public void update() {
        this.updateDate = LocalDateTime.now();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public abstract String getId();
}
