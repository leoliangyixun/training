/**
 * 
 */
package com.hujiang.notifycenter.tencent.vo;

import com.hujiang.basic.framework.rest.model.DataResult;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *  {@link DataResult} DataResult 中没有code字段， 为了兼容，提供HJDataResult
 * @author yangkai
 *
 */
public class HJDataResult<T> extends DataResult<T> {

    private int code;

    public HJDataResult() {

    }

    @Override
    public T getData() {
        return super.getData();
    }

    @Override
    public void setData(T data) {
        super.setData(data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


}
