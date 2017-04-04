package com.huacainfo.ace.common.fastdfs;

import java.io.File;
import java.io.OutputStream;

public interface IFile {
	public String saveFile(File file, String fileSaveName) throws Exception;

	public String saveFile(File file) throws Exception;

	public byte[] getFileBytes(String filePath, Long startPos, Long length)
			throws Exception;

	public void outputFile(String filePath, OutputStream output)
			throws Exception;

	public boolean deleteFile(String filePath) throws Exception;

	public long getSize(String filePath) throws Exception;

	public void copy(String sourcePath, String targetPath) throws Exception;
}
