package com.huacainfo.ace.gis.service;

import com.huacainfo.ace.gis.vo.GpsChianQVo;

public interface GpsChianDataznshService {
	public abstract Object selectListByPAreaName01(GpsChianQVo condition) throws Exception;
	public abstract Object selectListByPAreaName03(GpsChianQVo condition) throws Exception;
	public abstract Object selectListByPAreaName05(GpsChianQVo condition) throws Exception;
	public abstract Object selectListByPAreaName12(GpsChianQVo condition) throws Exception;
	public abstract Object selectListByPAreaName14(GpsChianQVo condition) throws Exception;
}
