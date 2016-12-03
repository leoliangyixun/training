/**
 * 
 */
package com.training.validator.model;

import lombok.Data;

/**
 * @author yangkai
 *
 */
@Data
public class SysUser {
    public String initiator;
    public Integer initiatorID;
    public String token;
    
	public SysUser() {}
}