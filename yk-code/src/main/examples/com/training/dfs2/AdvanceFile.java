package com.training.dfs2;


import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Collection;

/**
 * @author Jonathan Zhang
 * created at: 12/12/2016
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AdvanceFile extends SimpleFile {

	private static final long serialVersionUID = 3594904829700277599L;
	private String bucket;
    //customKey已被dfs禁用
    private String customKey;
    private String customPath;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
