/**
 * 
 */
package com.hujiang.fileupload.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.io.ContentLengthInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hujiang.basic.framework.plugin.dfs.model.FileInfo;
import com.hujiang.basic.framework.plugin.dfs.service.TakaClient;
import com.hujiang.basic.framework.rest.model.DataResult;

/**
 * @author yangkai
 *
 */
@RestController
@RequestMapping("/")
public class FileController {

	@Autowired
	TakaClient takaClient;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public DataResult<Object> ok() {
		
		return DataResult.ok();
	}
	
	@RequestMapping(value = "/upload/all", method = RequestMethod.POST)
	public DataResult<Object> uploadAll(@RequestParam("files") MultipartFile[] files) {

		return DataResult.ok();
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public DataResult<Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {

			String originalFilename = file.getOriginalFilename();
			takaClient.simpleUpload(file.getBytes());			
//			List<byte[]> contents = new ArrayList<>();
//			contents.add(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return DataResult.ok();
	}

	@RequestMapping(value = "/upload/advance", method = RequestMethod.POST)
	public DataResult<Object> advanceUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String originalFilename = file.getOriginalFilename();
		System.out.println(originalFilename);
		FileInfo info = new FileInfo();
		info.setBucket("cc-files");
		try {
			info.setContent(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		info.setCustomPath("/dsp/yangkai/yangkai_test.jpg");
		takaClient.advancedUpload(info);

		return DataResult.ok();
	}
	

	@RequestMapping(value = "/upload/advance/all", method = RequestMethod.POST)
	public DataResult<Object> advanceUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		List<FileInfo> infos = new ArrayList<>();
		for (int i = 0; i < files.length; i++){
			FileInfo info = new FileInfo();
			info.setBucket("cc-files");
			info.setRawFilename(files[i].getOriginalFilename());
			info.setCustomPath("/dsp/yangkai/yangkai_test" + i+ ".jpg");
			try {
				info.setContent(files[i].getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

			infos.add(info);
		}
		takaClient.advancedUpload(infos);

		return DataResult.ok();
	}

}
