/**
 * 
 */
package com.hujiang.notifycenter.push.core.validator;

import com.hujiang.notifycenter.gundam.common.model.constant.ResponseCodeEnum;
import com.hujiang.notifycenter.gundam.common.util.Preconditions;
import com.hujiang.notifycenter.push.model.dto.Badge;

/**
 * @author yangkai
 *
 */
public class BadgeValidator implements Validator<Badge> {

    @Override
    public boolean validate(ValidatorContext context, Badge target) {
        return validate(context, target, null);
    }

    @Override
    public boolean validate(ValidatorContext context, Badge target, ValidatorCallback callback) {
       Preconditions.notNull(target.getDeviceId(), ResponseCodeEnum.INVALID_DEVICE_ID);
       Preconditions.notNull(target.getAppUniqueId(), ResponseCodeEnum.INVALID_APP_UNIQUE_ID);
       Preconditions.notNull(target.getBadge(), ResponseCodeEnum.INVALID_BADGE);
       
       return true;
    }

}
