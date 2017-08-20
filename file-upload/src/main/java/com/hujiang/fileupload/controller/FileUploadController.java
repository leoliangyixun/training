/**
 * 
 */
package com.hujiang.fileupload.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hujiang.basic.framework.plugin.dfs.model.SimpleFile;
import com.hujiang.basic.framework.plugin.dfs.model.UploadInfo;
import com.hujiang.basic.framework.plugin.dfs.service.TakaService;
import com.hujiang.basic.framework.rest.model.DataResult;

/**
 * @author yangkai
 *
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

	@Autowired
	TakaService takaService;

	@RequestMapping(value = "/simple/all", method = RequestMethod.POST)
	public DataResult<Object> simpleUploadAll(@RequestParam("files") MultipartFile[] files) throws Exception {
		List<SimpleFile> simpleFiles = new ArrayList<>();

		for (MultipartFile file : files) {
			SimpleFile simpleFile = new SimpleFile();
			simpleFile.setRawFilename(file.getOriginalFilename());
			simpleFile.setContent(file.getBytes());
			simpleFiles.add(simpleFile);
		}
		
		Collection<UploadInfo> infos = takaService.simple().addAll(simpleFiles).upload();

		return DataResult.ok(infos);
	}
	
	@RequestMapping(value = "/simple", method = RequestMethod.POST)
	public DataResult<Object> simpleUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		SimpleFile simpleFile = new SimpleFile();
		simpleFile.setRawFilename(file.getOriginalFilename());
		simpleFile.setContent(file.getBytes());
		Collection<UploadInfo> infos = takaService.simple().add(simpleFile).upload();
		return DataResult.ok(infos);
	}

	@RequestMapping(value = "/advance", method = RequestMethod.POST)
	public DataResult<Object> advanceUpload(String bucket, String customKey, String customPath, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		SimpleFile simpleFile = new SimpleFile();
		simpleFile.setRawFilename(file.getOriginalFilename());
		simpleFile.setContent(file.getBytes());
	
		Collection<UploadInfo> infos = takaService.advance(bucket).add(simpleFile).upload();
		return DataResult.ok(infos);
	}

	@RequestMapping(value = "/advance/all", method = RequestMethod.POST)
	public DataResult<Object> advanceUploadAll(String customKey, String customPath, @RequestParam("files") MultipartFile[] files) throws Exception {
		List<SimpleFile> simpleFiles = new ArrayList<>();

		for (MultipartFile file : files) {
			SimpleFile simpleFile = new SimpleFile();
			simpleFile.setRawFilename(file.getOriginalFilename());
			simpleFile.setContent(file.getBytes());
			simpleFile.setCustomKey(customKey);
			simpleFile.setCustomPath(customPath );
			simpleFiles.add(simpleFile);
		}

		Collection<UploadInfo> infos = takaService.advance(null).addAll(simpleFiles).upload();

		return DataResult.ok(infos);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public DataResult<Object> uploadAll(
			@RequestParam("images") MultipartFile[] images, 
			@RequestParam("docks")  MultipartFile[] docks) throws Exception {
		System.out.println(images);
		System.out.println(docks);
		return DataResult.ok(null);
	}

}
