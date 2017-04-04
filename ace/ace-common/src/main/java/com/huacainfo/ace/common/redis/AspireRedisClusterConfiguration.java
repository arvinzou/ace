package com.huacainfo.ace.common.redis;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;
import static org.springframework.util.StringUtils.commaDelimitedListToSet;
import static org.springframework.util.StringUtils.split;

import java.util.Set;

import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;

public class AspireRedisClusterConfiguration extends RedisClusterConfiguration {
	/**
	 * 示例：127.0.0.1:23679,127.0.0.1:23680,127.0.0.1:23681
	 * 
	 * @param redisClusterNodes
	 */
	public void setRedisClusterNodes(String redisClusterNodes) {
		appendClusterNodes(commaDelimitedListToSet(redisClusterNodes));
	}

	protected void appendClusterNodes(Set<String> hostAndPorts) {
		for (String hostAndPort : hostAndPorts) {
			addClusterNode(readHostAndPortFromString(hostAndPort));
		}
	}

	protected RedisNode readHostAndPortFromString(String hostAndPort) {
		String[] args = split(hostAndPort, ":");
		notNull(args, "HostAndPort need to be seperated by  ':'.");
		isTrue(args.length == 2,
				"Host and Port String needs to specified as host:port");
		return new RedisNode(args[0], Integer.valueOf(args[1]).intValue());
	}
}
