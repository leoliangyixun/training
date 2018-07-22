package com.training.pass3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hujiang.foe.common.bean.exception.entity.ResponseEntity;
import com.hujiang.pass.integration.annotation.RequestTypeValidation;
import com.hujiang.pass.integration.annotation.RequiredLoginAnnotation;
import com.hujiang.pass.integration.annotation.TimerSteps;
import com.hujiang.pass.integration.interceptors.ClubAuthInterceptor;
import com.hujiang.pass.service.account.AccountSettingService;
import com.hujiang.pass.support.constants.RequestMsgType;
import com.hujiang.pass.support.model.dto.finance.*;
import com.hujiang.pass3.SetPayPasswordRes.SetPayPasswordDTO;

import com.training.pass3.SetPayPasswordRes.SetPayPasswordDTO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

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
    public ResponseEntity<BaseRes<SetPayPasswordDTO>> setPayPassport(@RequestBody SetPayPassword entity) {
        ResponseEntity<BaseRes<SetPayPasswordDTO>> re = null;
        re = this.responseHandle((r) -> {
            r.getRespCode();
            return accountSettingService.setPayPassword(ClubAuthInterceptor.getCurrentUser(), entity);
        });
        //---------------------------------------------------------------------------
        re = this.responseHandle(new ResponseHandler<String, BaseRes<?>>() {
            @Override
            public BaseRes<?> handle(String o) {
                return accountSettingService.setPayPassword(ClubAuthInterceptor.getCurrentUser(), entity);
            }
        });
        return re;
    }

}
