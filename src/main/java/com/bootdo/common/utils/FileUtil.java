package com.bootdo.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileUtil {

	/**
	 * 放回保存的绝对路径
	 * @param file
	 * @param filePath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
		return filePath+fileName;
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
            return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 生成一个随机的文件名
	 * @param fileName
	 * @return
	 */
	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
