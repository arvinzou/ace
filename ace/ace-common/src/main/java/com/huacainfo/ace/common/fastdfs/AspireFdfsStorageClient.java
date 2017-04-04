package com.huacainfo.ace.common.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.UploadStream;

public class AspireFdfsStorageClient extends StorageClient {
	public AspireFdfsStorageClient() {
		super();
	}

	/**
	 * constructor with tracker server and storage server
	 * 
	 * @param trackerServer
	 *            the tracker server, can be null
	 * @param storageServer
	 *            the storage server, can be null
	 */
	public AspireFdfsStorageClient(TrackerServer trackerServer,
			StorageServer storageServer) {
		super(trackerServer, storageServer);
	}

	protected String[] upload_file(byte cmd, String group_name,
			String local_filename, String file_ext_name,
			NameValuePair[] meta_list) throws IOException, Exception {
		File f = new File(local_filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);

			if (file_ext_name == null) {
				int nPos = local_filename.lastIndexOf('.');
				if (nPos > 0
						&& local_filename.length() - nPos <= ProtoCommon.FDFS_FILE_EXT_NAME_MAX_LEN + 1) {
					file_ext_name = local_filename.substring(nPos + 1);
				}
			}

			return this.do_upload_file(cmd, group_name, null, null,
					file_ext_name, f.length(),
					new UploadStream(fis, f.length()), meta_list);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}
}
