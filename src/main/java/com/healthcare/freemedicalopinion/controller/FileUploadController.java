package com.healthcare.freemedicalopinion.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.healthcare.freemedicalopinion.utility.AppConstants;

@Controller
public class FileUploadController {
	
	/**
	 * Common
	 * */
	@RequestMapping(value = "/secure/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestBody MultipartFile file) {
		if (!file.isEmpty()) {

			String newName = new Date().getTime() + file.getOriginalFilename();

			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(
								AppConstants.FILE_STORE_LOCATION + newName)));
				stream.write(bytes);
				stream.close();
				return newName;
			} catch (Exception e) {
				return "You failed to upload  => " + e.getMessage();
			}
		} else {
			return "You failed to upload  because the file was empty.";
		}
	}
}