package com.huacainfo.ace.common.fastdfs;

import java.net.InetSocketAddress;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

public class FastDfsConnectFactory extends
		BasePooledObjectFactory<TrackerServer> {
	/**
	 * 初始化
	 * 
	 * @param trackerServer
	 * @param connectTimeout
	 * @param networkTimeout
	 * @param charset
	 * @param httpPort
	 * @param stealToken
	 * @param secretKey
	 */
	public FastDfsConnectFactory(String trackerServers, int connectTimeout,
			int networkTimeout, String charset, int httpPort,
			boolean stealToken, String secretKey) {
		ClientGlobal.setG_anti_steal_token(stealToken);
		ClientGlobal.setG_charset(charset);
		ClientGlobal.setG_connect_timeout(connectTimeout);
		ClientGlobal.setG_network_timeout(networkTimeout);
		ClientGlobal.setG_secret_key(secretKey);
		// ClientGlobal.setG_tracker_http_port(httpPort);
		String[] trackerServer = trackerServers.split(",");
		java.net.InetSocketAddress[] addressList = new java.net.InetSocketAddress[trackerServer.length];
		for (int i = 0; i < trackerServer.length; i++) {
			String tmp[] = trackerServer[i].split(":");
			InetSocketAddress socketAddr = new InetSocketAddress(tmp[0],
					tmp.length > 1 ? Integer.valueOf(tmp[1]) : 22122);
			addressList[i] = socketAddr;
		}
		TrackerGroup group = new TrackerGroup(addressList);
		ClientGlobal.setG_tracker_group(group);
	}

	/**
	 * 建立新连接
	 */

	@Override
	public TrackerServer create() throws Exception {
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		ProtoCommon.activeTest(trackerServer.getSocket());
		return trackerServer;
	}

	@Override
	public PooledObject<TrackerServer> wrap(TrackerServer server) {
		return new DefaultPooledObject<TrackerServer>(server);
	}

}
