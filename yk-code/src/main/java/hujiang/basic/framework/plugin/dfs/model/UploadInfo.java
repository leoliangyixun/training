package com.hujiang.basic.framework.plugin.dfs.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Jonathan Zhang created at: 12/12/2016
 */
@Data
public class UploadInfo implements Serializable {

	private static final long serialVersionUID = -8337263486883958692L;
	private String fileId;
    private String publishUrl;
    private MetaData metaData;
    
    @Data
    public class MetaData implements Serializable {

		private static final long serialVersionUID = -306480522062916352L;
		private String rawFileName;
        private Integer size;
        private String hash;
        private String contentType;
        private Date uploadTime;
        private Integer width;
        private Integer height;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
