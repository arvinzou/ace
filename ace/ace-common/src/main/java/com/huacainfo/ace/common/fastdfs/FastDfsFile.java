package com.huacainfo.ace.common.fastdfs;

import java.io.File;
import java.io.OutputStream;

import org.apache.commons.pool2.ObjectPool;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FastDfsFile implements IFile {
	@Autowired
	private ObjectPool<TrackerServer> fastDfsPool = null;
	private Logger LOGGER = LoggerFactory.getLogger(FastDfsFile.class);

	public FastDfsFile() {
	}

	public FastDfsFile(ObjectPool<TrackerServer> fastDfsPool) {
		this.fastDfsPool = fastDfsPool;
	}

	private NameValuePair[] getNameValuePairs(String fileName) {
		NameValuePair[] meta_list = new NameValuePair[1];
		meta_list[0] = new NameValuePair("fileName", fileName);
		return meta_list;
	}

	private String getFileExtName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
	}

	@Override
	public String saveFile(File file) throws Exception {
		return saveFile(file, file.getName());
	}

	@Override
	public String saveFile(File file, String fileSaveName) throws Exception {
		TrackerServer trackerServer = null;
		try {
			trackerServer = this.getTrackerServer();
			AspireFdfsStorageClient storageClient = getStorageClient(trackerServer);
			String[] rst = storageClient.upload_file(file.getAbsolutePath(),
					getFileExtName(fileSaveName),
					getNameValuePairs(fileSaveName));
			if (rst == null || rst.length < 2) {
				throw new java.lang.Exception("保存文件失败,错误码："
						+ storageClient.getErrorCode());
			}
			LOGGER.info("保存文件成功,返回：" + rst[0] + "/" + rst[1]);
			return rst[0] + "/" + rst[1] + "?filename=" + fileSaveName;
		} finally {
			if (trackerServer != null) {
				fastDfsPool.returnObject(trackerServer);
			}
		}

	}

	public byte[] getFileBytes(String groupName, String fileName,
			Long startPos, int length) throws Exception {
		TrackerServer trackerServer = null;
		try {
			trackerServer = getTrackerServer();
			StorageClient storageClient = getStorageClient(trackerServer);
			byte[] b = null;
			b = storageClient.download_file(groupName, fileName, startPos,
					length);
			return b;
		} finally {
			if (trackerServer != null) {
				fastDfsPool.returnObject(trackerServer);
			}
		}
	}

	public boolean deleteFile(String groupName, String fileName)
			throws Exception {
		TrackerServer trackerServer = null;
		try {
			trackerServer = getTrackerServer();
			StorageClient storageClient = getStorageClient(trackerServer);
			int size = storageClient.delete_file(groupName, fileName);
			LOGGER.info("删除文件：" + fileName + ",返回值：" + size);
			if (size == 0) {
				return true;
			} else {
				return false;
			}
		} finally {
			if (trackerServer != null) {
				fastDfsPool.returnObject(trackerServer);
			}
		}
	}

	private TrackerServer getTrackerServer() throws Exception {
		return fastDfsPool.borrowObject();
	}

	private AspireFdfsStorageClient getStorageClient(TrackerServer trackerServer)
			throws Exception {
		StorageServer storageServer = null;
		AspireFdfsStorageClient storageClient = new AspireFdfsStorageClient(
				trackerServer, storageServer);
		return storageClient;
	}

	private long getSize(String groupName, String fileName) throws Exception {
		long length = 0l;
		TrackerServer trackerServer = null;
		try {
			trackerServer = getTrackerServer();
			StorageClient storageClient = getStorageClient(trackerServer);
			FileInfo fi = storageClient.get_file_info(groupName, fileName);
			if (fi != null) {
				length = fi.getFileSize();
			}
			return length;
		} finally {
			if (trackerServer != null) {
				fastDfsPool.returnObject(trackerServer);
			}
		}
	}

	private byte[] getFileBytes(String groupName, String path, Long startPos,
			Long length) throws Exception {
		TrackerServer trackerServer = getTrackerServer();
		try {
			StorageClient storageClient = getStorageClient(trackerServer);
			byte[] b = storageClient.download_file(groupName, path, startPos,
					length);
			return b;
		} finally {
			if (trackerServer != null) {
				fastDfsPool.returnObject(trackerServer);
			}
		}
	}

	private String[] parseGroupAndPath(String filePath) {
		if (filePath.indexOf("?") > 0) {
			filePath = filePath.substring(0, filePath.indexOf("?"));
		}
		String groupName = filePath.substring(0, filePath.indexOf("/"));
		String path = filePath.substring(filePath.indexOf("/") + 1,
				filePath.length());
		return new String[] { groupName, path };
	}

	@Override
	public byte[] getFileBytes(String filePath, Long startPos, Long length)
			throws Exception {
		String[] groupAndPath = parseGroupAndPath(filePath);
		return getFileBytes(groupAndPath[0], groupAndPath[1], startPos, length);
	}

	@Override
	public boolean deleteFile(String filePath) throws Exception {
		String[] groupAndPath = parseGroupAndPath(filePath);
		deleteFile(groupAndPath[0], groupAndPath[1]);
		return true;
	}

	@Override
	public long getSize(String filePath) throws Exception {
		String[] groupAndPath = parseGroupAndPath(filePath);
		return getSize(groupAndPath[0], groupAndPath[1]);
	}

	@Override
	public void copy(String sourcePath, String targetPath) throws Exception {

	}

	@Override
	public void outputFile(String filePath, OutputStream output)
			throws Exception {
		long fileSize = getSize(filePath);
		long endPos = fileSize;
		long startPos = 0;
		if (endPos > 10 * 1024) {
			endPos = 10 * 1024;
		}
		while (startPos < fileSize) {
			if (endPos > fileSize) {
				endPos = fileSize;
			}
			byte[] bytes = getFileBytes(filePath, startPos, endPos - startPos);
			output.write(bytes);
			output.flush();
			startPos = endPos;
			endPos += 10 * 1024;
		}

	}

}
