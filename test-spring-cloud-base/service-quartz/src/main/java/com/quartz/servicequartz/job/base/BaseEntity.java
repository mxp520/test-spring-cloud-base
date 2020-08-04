package com.quartz.servicequartz.job.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 创建者
     */
    private Long  creator;
    /**
     * 创建时间
     */
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
