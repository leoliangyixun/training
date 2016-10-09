/**
 * 
 */
package com.training.dao.model;

import java.util.Date;

/**
 * @author yangkai
 *
 */
public interface Entity {
    
    void setId(Integer id);

    Integer getId();
    
    void setCreateTime(Date createTime);
    
    Date getCreateTime();
    
    void setUpdateTime(Date updateTime);
    
    Date getUpdateTime();

    String namespace();

}
