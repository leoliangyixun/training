/**
 * 
 */
package com.training.pass3;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hujiang.pass3.SetPayPasswordRes.SetPayPasswordDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangkai
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class SetPayPasswordRes extends BaseFinanceRes<SetPayPasswordDTO> {

    private static final long serialVersionUID = -5138166806533045631L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Data
    public static class SetPayPasswordDTO implements Serializable {
 
        private static final long serialVersionUID = 5599806660938941962L;
        private Integer respCode;

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }
}
