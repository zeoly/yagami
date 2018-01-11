package com.yahacode.yagami.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Base entity model, recommend to extend this to build your own business model
 *
 * @author zengyongli
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 2305562441218971838L;

    /**
     * create date
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
    private Date createDate;

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
    private Date updateDate;

    /**
     * update person code
     */
    @Column(name = "update_by")
    private String updateBy;

    public BaseModel(String peopleCode) {
        this.createDate = new Date();
        this.updateDate = new Date();
        this.createBy = peopleCode;
        this.updateBy = peopleCode;
    }

    public BaseModel() {
    }

    public void init(String peopleCode) {
        this.createDate = new Date();
        this.updateDate = new Date();
        this.createBy = peopleCode;
        this.updateBy = peopleCode;
    }

    public void update(String peopleCode) {
        this.updateDate = new Date();
        this.updateBy = peopleCode;
    }

    public void update() {
        this.updateDate = new Date();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
