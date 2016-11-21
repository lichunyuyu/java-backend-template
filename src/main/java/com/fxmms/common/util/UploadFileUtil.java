package com.fxmms.common.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {
	
	public static String REALPATH = "/attached/fileupload/";

	/**
	 * 功能描述：把上传的文件存到相应的磁盘位置当中
	 * @param newFileName
	 * @param filedata
	 */
	public static String getUrlAndSaveFile(MultipartFile filedata,HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("attached/fileupload/");
		if (filedata != null && !filedata.isEmpty()) {
			// 获取图片的文件名
			String fileName = filedata.getOriginalFilename();
			// 获取图片的扩展名
			String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 新的图片文件�?= 获取时间�?"."图片扩展�?
			String newFileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+(int)(Math.random()*1000+1000)+ "." + extensionName;
			@SuppressWarnings("deprecation")
			String basePath = request.getRealPath("/")+REALPATH;
			String fullpath = basePath +newFileName;
			try {
				filedata.transferTo(new File(fullpath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			buffer.append(newFileName);
		}
		return buffer.toString();
	}
}
