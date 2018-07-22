package com.training.pass2;

import com.hujiang.pass.integration.annotation.RequestTypeValidation;
import com.hujiang.pass.integration.annotation.RequiredLoginAnnotation;
import com.hujiang.pass.integration.annotation.TimerSteps;
import com.hujiang.pass.integration.interceptors.ClubAuthInterceptor;
import com.hujiang.pass.service.account.AccountSettingService;
import com.hujiang.pass.support.constants.RequestMsgType;
import com.hujiang.pass.support.model.dto.finance.*;
import com.hujiang.pass.support.model.dto.finance.res.SetPayPasswordRes;
import com.hujiang.pass.support.model.dto.finance.res.SetPayPasswordRes.SetPayPasswordDTO;
import com.hujiang.pass.support.model.dto.res.BaseRes;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资金账户设置Api
 * Created by yangkai on 2017/5/26.
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AccountSettingController extends BaseController {

    @Autowired
    private AccountSettingService accountSettingService;

    @ApiOperation(value = "设置支付密码", httpMethod = "POST", response = ResponseEntity.class)
    @RequiredLoginAnnotation
    @RequestTypeValidation(types = {RequestMsgType.ResetPassword})
    @TimerSteps(isMustToken = true)
    @RequestMapping(value = "/pay/password/set", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> setPayPassport(@RequestBody SetPayPassword entity) {
        ResponseEntity<BaseRes<?>> re = this.responseHandle((r) -> accountSettingService.setPayPassword(ClubAuthInterceptor.getCurrentUser(), entity));
        return re;
    }

}
