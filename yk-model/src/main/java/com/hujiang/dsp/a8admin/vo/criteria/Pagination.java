package com.hujiang.dsp.a8admin.vo.criteria;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by yangkai on 2017/2/16.
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class Pagination extends Sort implements Pageable, Serializable {

	private static final long serialVersionUID = 7700735669700757829L;
	private Integer limit = 20;
    private Integer pageNo = 1;

}
