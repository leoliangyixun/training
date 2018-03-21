/**
 * 
 */
package com.training.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangkai
 *
 */
public abstract class PersistentEntity implements Entity, Serializable {

    private static final long serialVersionUID = 1L;

    public Integer id;
    public Date createTime;
    public Date updateTime;

    public PersistentEntity() {
      
    }

    @Override
    public void setId(Integer id) {
       this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setCreateTime(Date createTime) {
       this.createTime = createTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

}
