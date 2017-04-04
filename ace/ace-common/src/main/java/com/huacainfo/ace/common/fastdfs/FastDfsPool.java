package com.huacainfo.ace.common.fastdfs;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.fastdfs.TrackerServer;

public class FastDfsPool extends GenericObjectPool<TrackerServer> {

	public FastDfsPool(PooledObjectFactory<TrackerServer> factory,
			GenericObjectPoolConfig config, AbandonedConfig abandonedConfig) {
		super(factory, config, abandonedConfig);
	}

	public FastDfsPool(PooledObjectFactory<TrackerServer> factory,
			GenericObjectPoolConfig config) {
		super(factory, config);
	}

	public FastDfsPool(PooledObjectFactory<TrackerServer> factory) {
		super(factory);
	}

}
