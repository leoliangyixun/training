/**
 * 
 */
package com.hujiang.pass3;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangkai
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public abstract class BaseFinanceRes<T> extends BaseRes<T> {

    private static final long serialVersionUID = 3080879889960539469L;

    /// 用于脱敏的展示
    protected static final String sensitive = "******";

    /**
     * 将敏感属性脱敏
     */
    public String clearSensitive() {
        BaseFinanceRes<T> res = JsonUtil.deserialize(JsonUtil.serialize(this));
        return res.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
