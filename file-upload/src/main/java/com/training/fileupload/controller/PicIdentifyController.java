/**
 * 
 */
package com.training.fileupload.controller;

import java.io.File;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hujiang.basic.framework.plugin.picidentify.PicIdentifyService;
import com.hujiang.basic.framework.plugin.picidentify.constants.IdentifyProvider;
import com.hujiang.basic.framework.plugin.picidentify.model.FileList;
import com.hujiang.basic.framework.rest.model.DataResult;

/**
 * @author yangkai
 *
 */
@RestController
@RequestMapping("/identify")
public class PicIdentifyController {

	@Autowired
	PicIdentifyService picIdentifyService;

	@RequestMapping(method = RequestMethod.GET)
	public DataResult<Object> ok() {
		return DataResult.ok();
	}

	@RequestMapping(value = "/porn", method = RequestMethod.POST)
	public DataResult<Object> porn(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		File f = null;
		try {
			f = new File("/Users/yangkai/Develop/pictures/" + file.getOriginalFilename());
			file.transferTo(f);
			Collection<FileList> infos = picIdentifyService.fileBuilder().add(f).build(IdentifyProvider.QCLOUD).porn();
			return DataResult.ok(infos);
		} catch (Exception e) {
			throw e;
		} finally {
			if (f != null && f.exists() && f.isFile()) {
				f.delete();
			}
		}
	}

	@RequestMapping(value = "/face", method = RequestMethod.POST)
	public DataResult<Object> face(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		File f = null;
		try {
			f = new File("/Users/yangkai/Develop/pictures/" + file.getOriginalFilename());
			file.transferTo(f);
			Collection<FileList> infos = picIdentifyService.fileBuilder().add(f).build().face();
			return DataResult.ok(infos);
		} catch (Exception e) {
			throw e;
		} finally {
			if (f != null && f.exists() && f.isFile()) {
				f.delete();
			}
		}
	}
	

	@RequestMapping(value = "/detect", method = RequestMethod.POST)
	public DataResult<Object> detect(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		//TODO
		return null;
	}

}
