package com.hujiang.dsp.a8admin.vo.criteria;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by yangkai on 2017/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AdSpaceCriteria extends CommonCriteria {

    private Integer siteId;
    private Integer channelId;
}
