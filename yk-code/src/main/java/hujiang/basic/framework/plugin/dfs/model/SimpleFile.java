package com.hujiang.basic.framework.plugin.dfs.model;


import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

/**
 * @author Jonathan Zhang
 * created at: 12/12/2016
 */
@Data
public class SimpleFile implements Serializable {

	private static final long serialVersionUID = -3755083123073727844L;
	private String rawFilename;
    private byte[] content;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
