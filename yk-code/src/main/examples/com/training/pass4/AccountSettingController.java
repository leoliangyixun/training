package com.training.pass4;

import com.hujiang.pass.account.config.ResponseHandler;
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

import java.io.Serializable;

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
        ResponseEntity<BaseRes<?>> re = null;

        re = this.responseHandle((r) -> {


         return accountSettingService.setPayPassword(ClubAuthInterceptor.getCurrentUser(), entity);
        }, () -> 2);

        return re;
    }

    @ApiOperation(value = "验证支付密码", httpMethod = "POST", response = ResponseEntity.class)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    @RequestMapping(value = "/pay/password/validate", method = RequestMethod.POST)
    public ResponseEntity<BaseRes<?>> validatePayPassword(@RequestBody ValidatePayPassword entity) {
        return this.responseHandle(() -> accountSettingService.validatePayPassword(ClubAuthInterceptor.getCurrentUser(), entity));
    }

    @ApiOperation(value = "修改支付密码", httpMethod = "PUT", response = ResponseEntity.class)
    @RequiredLoginAnnotation
    @RequestTypeValidation(types = {RequestMsgType.ModifyPayPassword})
    @TimerSteps(isMustToken = true)
    @RequestMapping(value = "/pay/password/modify", method = RequestMethod.PUT)
    public ResponseEntity<BaseRes<?>> modifyPayPassword(@RequestBody ModifyPayPassword entity) {
        return this.responseHandle(() -> accountSettingService.modifyPayPassword(ClubAuthInterceptor.getCurrentUser(), entity));
    }


    @ApiOperation(value = "重置支付密码", httpMethod = "PUT", response = ResponseEntity.class)
    @RequestMapping(value = "/pay/password/reset", method = RequestMethod.PUT)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    @RequestTypeValidation(types = {RequestMsgType.ResetPayPassword})
    public ResponseEntity<BaseRes<?>> resetPayPassword(@RequestBody ResetPayPassword entity) {
        return this.responseHandle(() -> accountSettingService.resetPayPassword(ClubAuthInterceptor.getCurrentUser(), entity));
    }

    @ApiOperation(value = "申请生成重置支付密码链接", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/pay/password/applyreset", method = RequestMethod.POST)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    @RequestTypeValidation(types = {RequestMsgType.ResetPayPassword})
    public ResponseEntity<BaseRes<?>> applyResetPayPassowrd(@RequestBody ApplyToken entity) {
        return this.responseHandle(() -> accountSettingService.applyResetPayPassword(ClubAuthInterceptor.getCurrentUser(), entity));
    }

    @ApiOperation(value = "金融线重置支付密码验证token", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/pay/password/validatetoken", method = RequestMethod.POST)
    @RequiredLoginAnnotation
    public ResponseEntity<BaseRes<?>> resetValidateToken(@RequestBody ResetValidateToken entity) {
        return this.responseHandle(() -> accountSettingService.validatePayPasswordToken(ClubAuthInterceptor.getCurrentUser(), entity));
    }

    @ApiOperation(value = "绑定银行卡", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/bankcard/bind", method = RequestMethod.POST)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    public ResponseEntity<BaseRes<?>> bindBankCard(@RequestBody BindBankCard entity) {
        return this.responseHandle(() -> accountSettingService.bindBankCard(ClubAuthInterceptor.getCurrentUser(), entity));
    }
    
    @ApiOperation(value = "验证银行卡", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/bankcard/validate", method = RequestMethod.POST)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    @RequestTypeValidation(types={ RequestMsgType.ResetPayPassword })
    public ResponseEntity<BaseRes<?>> validateBankCard(@RequestBody ValidateBankCard entity) {
        return this.responseHandle(() -> accountSettingService.validateBankCard(ClubAuthInterceptor.getCurrentUser(), entity));
    }
    
    @ApiOperation(value = "验证身份证", httpMethod = "POST", response = ResponseEntity.class)
    @RequestMapping(value = "/idno/validate", method = RequestMethod.POST)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    @RequestTypeValidation(types={ RequestMsgType.ResetPayPassword })
    public ResponseEntity<BaseRes<?>> validateIdNo(@RequestBody ValidateIdNo entity) {
        return this.responseHandle(() -> accountSettingService.validateIdNo(ClubAuthInterceptor.getCurrentUser(), entity));
    }
    
    @ApiOperation(value = "获取实名认证信息", httpMethod = "GET", response = ResponseEntity.class)
    @RequestMapping(value = "/finace/realname", method = RequestMethod.GET)
    @RequiredLoginAnnotation
    @TimerSteps(isMustToken = true)
    @RequestTypeValidation(types={ RequestMsgType.ResetPayPassword })
    public ResponseEntity<BaseRes<?>> queryIdNo() {
        return this.responseHandle(() -> accountSettingService.queryIdNo(ClubAuthInterceptor.getCurrentUser()));
    }

    /// 验证账户状态
    /// businessCode：
    /// 绑卡实名认证业务code ＝ "2001"
    /// 检查用户支付帐户冻结状态 = "1001"
    /// 重置密码身份证号校验 = "3001"
    @ApiOperation(value = "验证账户状态", httpMethod = "GET", response = ResponseEntity.class)
    @RequestMapping(value = "/finace/status", method = RequestMethod.GET)
    @RequiredLoginAnnotation
    public ResponseEntity<BaseRes<?>> validateAccountStatus(ValidateAccountStatus entity) {
        return this.responseHandle(() -> accountSettingService.validateAccountStatus(ClubAuthInterceptor.getCurrentUser(), entity));
    }
    
    @ApiOperation(value = "获取银行列表", httpMethod = "GET", response = ResponseEntity.class)
    @RequestMapping(value = "/bank/bankinfos", method = RequestMethod.GET)
    @RequiredLoginAnnotation
    public ResponseEntity<BaseRes<?>> queryBankInfo() {
        return this.responseHandle(() -> accountSettingService.queryBankInfo());
    }

}
