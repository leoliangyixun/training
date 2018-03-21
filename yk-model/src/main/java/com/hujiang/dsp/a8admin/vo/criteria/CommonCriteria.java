package com.hujiang.dsp.a8admin.vo.criteria;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by yangkai on 2017/2/26.
 */
@Data
public class CommonCriteria extends BaseCriteria {

    private static final long serialVersionUID = -2291714501776080172L;
    private Integer adTypeId;
    private Integer sizeId;
}
