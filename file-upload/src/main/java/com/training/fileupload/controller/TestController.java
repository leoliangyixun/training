/**
 * 
 */
package com.training.fileupload.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.basic.framework.core.dao.pagination.Page;
import com.hujiang.basic.framework.rest.model.DataResult;

/**
 * @author yangkai
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public DataResult<Object> uploadAll(HttpServletRequest request) throws Exception {

		JSONObject obj = new JSONObject();
		obj.put("getRemoteHost()", request.getRemoteHost());
		obj.put("getContextPath()", request.getContextPath());
		obj.put("getServerName()", request.getServerName());
		obj.put("getRequestURI()", request.getRequestURI());
		return DataResult.ok(obj);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public DataResult<Object> test(HttpServletRequest request) throws Exception {

		Page<String> page = new Page<>(0, 10);
		return DataResult.ok();
	}

}
