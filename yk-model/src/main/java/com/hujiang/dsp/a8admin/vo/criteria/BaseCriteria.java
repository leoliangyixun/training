package com.hujiang.dsp.a8admin.vo.criteria;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yangkai on 2017/2/16.
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class BaseCriteria extends Pagination {

	private static final long serialVersionUID = -55529971038620817L;
	private String keyword;
    private Integer status;

}
