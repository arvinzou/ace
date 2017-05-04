package com.huacainfo.ace.gis.service.impl;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.gis.dao.GpsChianDatajzjzMapper;
import com.huacainfo.ace.gis.service.GpsChianDatajzjzService;
import com.huacainfo.ace.gis.vo.GpsChianQVo;

@Service("gpsChianDatajzjzService")
public class GpsChianDatajzjzServiceImpl implements GpsChianDatajzjzService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GpsChianDatajzjzMapper gpsChianjzjzMapper;


	public Object selectListByPAreaName01(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianjzjzMapper
				.selectListByPAreaName01(condition.getAreaName());
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> data3 = new HashMap<String, Object>();
		List<List<Object>> data4 = new ArrayList<List<Object>>();
		List<String> areaNames = new ArrayList<String>();
		
		List<Map<String, Object>> listall = this.gpsChianjzjzMapper
				.selectListAll();
		// data1 资金
		for (Map<String, Object> o : list) {
			Map<String, Object> e = new HashMap<String, Object>();
			e.put("name", o.get("area_name"));
			e.put("value", o.get("zj"));
			data1.add(e);
			areaNames.add((String) o.get("area_name"));
		}
		rst.put("data1", data1);
		// data2 参合
		for (Map<String, Object> o : list) {
			Map<String, Object> e = new HashMap<String, Object>();
			e.put("name", o.get("area_name"));
			e.put("value", o.get("chrs"));
			data2.add(e);
		}
		rst.put("data2", data2);
		// data3
		for (Map<String, Object> o : listall) {
			List<Object> e = new ArrayList<Object>();
			e.add(o.get("gps_x"));
			e.add(o.get("gps_y"));
			data3.put((String) o.get("area_name"), e);
		}
		
		rst.put("data3", data3);

		// data4
		Date randomDate = CommonUtils.randomDate("2016-01-01", CommonUtils.parseDate(new Date())); 
		
		for (Map<String, Object> o : list) {
			List<Object> e = new ArrayList<Object>();
			Map<String, Object> m1 = new HashMap<String, Object>();
			Map<String, Object> m2 = new HashMap<String, Object>();
			List<Map<String,Object>> rs=this.gpsChianjzjzMapper.selectPersonByDateAndAreaCode((String)o.get("area_code"), CommonUtils.parseDate(randomDate));
			m1.put("name", o.get("area_name"));
			
			if(!rs.isEmpty()){
				Map<String,Object> p=rs.get(0);
				m1.put("value",p.get("rc"));
				m2.put("name", p.get("area_name"));
				e.add(m1);
				e.add(m2);
				data4.add(e);
			}
			//this.logger.info(m1);
			//this.logger.info(m2);
			
		}
		rst.put("data4", data4);
		return rst;
	}
	


	
	public Object selectListByPAreaName03(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianjzjzMapper
				.selectListByPAreaName03(condition.getAreaName());
		List<String> areaNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		String name = "";
		for (Map<String, Object> o : list) {
			name = (String) o.get("area_name");
			if (name.startsWith("六盘水")) {
				name = name.substring(0, 3);
			} else {
				name = name.substring(0, 2);
			}
			areaNames.add(name);
			values.add(o.get("chrs"));
		}
		rst.put("datax", areaNames);
		rst.put("datay", values);
		return rst;
	}

	
	/* 资金占比 */
	public Object selectListByPAreaName05(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = this.gpsChianjzjzMapper
				.selectListByPAreaName05(condition.getAreaName());
		Map<String, Object> p = list.get(0);
		// data1 资金
		String key="nh";
		for (String o : new String[] {"农合","民政","商保"}) {
			if(o.equals("农合")){
				key="nh";
			}
			if(o.equals("民政")){
				key="mz";
			}
			if(o.equals("商保")){
				key="sb";
			}
			Map<String, Object> e = new HashMap<String, Object>();
			e.put("name", o);
			e.put("value",p.get(key));
			this.logger.info(e);
			data1.add(e);
		}
		rst.put("data1", data1);
		return rst;
	}

	public Object selectListByPAreaName12(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianjzjzMapper
				.selectListByPAreaName12(params);
		rst.put("data1", list);
		return rst;
	}
	
	public Object selectListByPAreaName14(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<String> areaNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		List<Object> values2 = new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianjzjzMapper
				.selectListByPAreaName14(params);
		for (Map<String, Object> o : list) {
			areaNames.add( (String) o.get("name"));
			values.add(o.get("value"));
			values2.add(o.get("value2"));
		}
		if(list.isEmpty()){
			areaNames.add( "无");
			values.add(0);
			values2.add(0);
		}
		rst.put("datax", areaNames);
		rst.put("datay", values);
		rst.put("datay2", values2);
		return rst;
	}
	
}
