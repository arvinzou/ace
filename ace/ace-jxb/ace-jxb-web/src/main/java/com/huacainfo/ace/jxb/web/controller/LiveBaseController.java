package com.huacainfo.ace.jxb.web.controller;

import java.io.Serializable;
import java.util.Map;

import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.web.controller.BaseController;

public class LiveBaseController extends BaseController implements
		Serializable {
	private static final long serialVersionUID = 1L;
	public int defaultPageSize = 10;

	public Map<String, Object> getPageParam(int page, Map<String, Object> p) {
		if (page <= 1) {
			p.put("start", 0);
			p.put("end", defaultPageSize);
		} else {
			p.put("start", (page - 1) * defaultPageSize);
			p.put("end",  defaultPageSize);
		}
		p.put("fastdfs_server", PropertyUtil.getProperty("fastdfs_server"));
		return p;
	}

	
}
