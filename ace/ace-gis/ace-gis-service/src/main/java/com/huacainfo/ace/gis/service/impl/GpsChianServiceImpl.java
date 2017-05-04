package com.huacainfo.ace.gis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.huacainfo.ace.gis.dao.GpsChianMapper;
import com.huacainfo.ace.gis.service.GpsChianService;
import com.huacainfo.ace.gis.vo.GpsChianQVo;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gpsChianService")
public class GpsChianServiceImpl implements GpsChianService {
	Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private GpsChianMapper gpsChianMapper;


	public Object selectListByPAreaName01(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName01(condition.getAreaName());
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> data3 = new HashMap<String, Object>();
		List<List<Object>> data4 = new ArrayList<List<Object>>();
		List<String> areaNames = new ArrayList<String>();
		
		List<Map<String, Object>> listall = this.gpsChianMapper
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
		Date randomDate = CommonUtils.randomDate("2015-01-01", CommonUtils.parseDate(new Date())); 
		
		for (Map<String, Object> o : list) {
			List<Object> e = new ArrayList<Object>();
			Map<String, Object> m1 = new HashMap<String, Object>();
			Map<String, Object> m2 = new HashMap<String, Object>();
			List<Map<String,Object>> rs=this.gpsChianMapper.selectPersonByDateAndAreaCode((String)o.get("area_code"), CommonUtils.parseDate(randomDate));
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
	
	/* 参合指标 */
	public Object selectListByPAreaName02(GpsChianQVo condition)
			throws Exception {
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName02(condition.getAreaName());
		Map<String, Object> p = list.get(0);
		Map<String, Object> rst = new HashMap<String, Object>();
		rst.put("chrs", p.get("chrs"));// 参合人数
		rst.put("zjsy", p.get("zjsy"));// 资金使用
		rst.put("chl", p.get("chl"));// 参合率
		rst.put("zjsyl", p.get("zjsyl"));// 资金使用率
		rst.put("dntcjjsyl", p.get("zjsyl"));// 统筹基金使用率
		rst.put("dncjzj", p.get("dncjzj"));// 筹资资金
		rst.put("zcfwnbxbl", p.get("zcfwnbxbl"));// 政策范围内报销比
		return rst;
	}

	/* 参合情况统计 */
	public Object selectListByPAreaName03(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
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

	public Object selectListByPAreaName04(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();

		return rst;
	}

	/* 资金占比 */
	public Object selectListByPAreaName05(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = this.gpsChianMapper
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

	public Object selectListByPAreaName06(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName06(condition.getAreaName());
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> data3 = new HashMap<String, Object>();
		List<List<Object>> data4 = new ArrayList<List<Object>>();
		List<String> areaNames = new ArrayList<String>();
		
		List<Map<String, Object>> listall = this.gpsChianMapper
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
		Date randomDate = CommonUtils.randomDate("2015-01-01", CommonUtils.parseDate(new Date())); 
		
		for (Map<String, Object> o : list) {
			List<Object> e = new ArrayList<Object>();
			Map<String, Object> m1 = new HashMap<String, Object>();
			Map<String, Object> m2 = new HashMap<String, Object>();
			List<Map<String,Object>> rs=this.gpsChianMapper.selectPersonByDateAndAreaCode((String)o.get("area_code"), CommonUtils.parseDate(randomDate));
			m1.put("name", o.get("area_name"));
			
			if(!rs.isEmpty()){
				Map<String,Object> p=rs.get(0);
				m1.put("value",p.get("rc"));
				m2.put("name", p.get("area_name"));
				e.add(m1);
				e.add(m2);
				data4.add(e);
			}
			this.logger.info(m1);
			this.logger.info(m2);
		}
		rst.put("data4", data4);
		return rst;
	}

	public Object selectListByPAreaName07(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Object> dateShow=new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianMapper.selectListByPAreaName07(condition.getAreaName());
		if(list==null||list.size()==0){
			dateShow.add(0);
			dateShow.add(0);
		}
        for (Map<String, Object> map : list) {
        	dateShow.add(map.get("COUNT"));
		 }
        rst.put("data", dateShow);
		return rst;
	}

	public Object selectListByPAreaName08(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Object> dateShow=new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianMapper.selectListByPAreaName08(condition.getAreaName());
		if(list==null||list.size()==0){
			dateShow.add(0);
			dateShow.add(0);
		}
        for (Map<String, Object> map : list) {
        	dateShow.add(map.get("COUNT"));
		 }
        rst.put("data", dateShow);
		return rst;
	}

	public Object selectListByPAreaName09(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Object> dateShow=new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianMapper.selectListByPAreaName09(condition.getAreaName());
		if(list==null||list.size()==0){
			dateShow.add(0);
			dateShow.add(0);
		}
        for (Map<String, Object> map : list) {
        	dateShow.add(map.get("COUNT"));
		 }
        rst.put("data", dateShow);
		return rst;
	}
	/*疾病用药分析*/
	public Object selectListByPAreaName10(GpsChianQVo condition)
			throws Exception {
		this.logger.info(condition.toString());
		
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		this.logger.info(params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName10(params);
		
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> data3 = new HashMap<String, Object>();
		List<List<Object>> data4 = new ArrayList<List<Object>>();
		List<String> areaNames = new ArrayList<String>();
		List<Map<String, Object>> listall = this.gpsChianMapper
				.selectListAll();
		// data1 疾病
		for (Map<String, Object> o : list) {
			Map<String, Object> e = new HashMap<String, Object>();
			e.put("name", o.get("area_name"));
			e.put("value", o.get("jbfy"));
			data1.add(e);
			areaNames.add((String) o.get("area_name"));
		}
		rst.put("data1", data1);
		// data2 用药
		for (Map<String, Object> o : list) {
			Map<String, Object> e = new HashMap<String, Object>();
			e.put("name", o.get("area_name"));
			e.put("value", o.get("ypfy"));
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
		Date randomDate = CommonUtils.randomDate("2015-01-01", CommonUtils.parseDate(new Date())); 
		for (Map<String, Object> o : list) {
			List<Object> e = new ArrayList<Object>();
			Map<String, Object> m1 = new HashMap<String, Object>();
			Map<String, Object> m2 = new HashMap<String, Object>();
			List<Map<String,Object>> rs=this.gpsChianMapper.selectPersonByDateAndAreaCode((String)o.get("area_code"), CommonUtils.parseDate(randomDate));
			m1.put("name", o.get("area_name"));
			
			if(!rs.isEmpty()){
				Map<String,Object> p=rs.get(0);
				m1.put("value",p.get("rc"));
				m2.put("name", p.get("area_name"));
				e.add(m1);
				e.add(m2);
				data4.add(e);
			}
			this.logger.info(m1);
			this.logger.info(m2);
		}
		rst.put("data4", data4);
		return rst;
	}
	/*疾病用药指标*/
	public Object selectListByPAreaName11(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName11(params);
		Map<String, Object> p = list.get(0);
		Map<String, Object> rst = new HashMap<String, Object>();
		rst.put("fbcs", p.get("fbcs"));// 发病次数
		rst.put("syzjzb1", p.get("syzjzb1"));// 使用资金占比
		rst.put("yyje", p.get("yyje"));// 用药金额
		rst.put("syzjzb2", p.get("syzjzb2"));// 使用资金占比
		return rst;
	}
	/*疾病TOP10滚动表格*/
	public Object selectListByPAreaName12(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName12(params);
		rst.put("data1", list);
		return rst;
	}
	/*用药TOP10滚动表格*/
	public Object selectListByPAreaName13(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName13(params);
		rst.put("data1", list);
		return rst;
	}
	/*疾病TOP10柱形图*/
	public Object selectListByPAreaName14(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<String> areaNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		List<Object> values2 = new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
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
	/*用药TOP10柱形图*/
	public Object selectListByPAreaName15(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<String> areaNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		List<Object> values2 = new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName15(params);
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
	/*系统用户滚动表格*/
	public Object selectListByPAreaName16(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(condition, params);
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName16(params);
		rst.put("data1", list);
		return rst;
	}
	
	public Object selectListByPAreaName17(GpsChianQVo condition)
			throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list = this.gpsChianMapper
				.selectListByPAreaName01(condition.getAreaName());
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> data3 = new HashMap<String, Object>();
		List<List<Object>> data4 = new ArrayList<List<Object>>();
		List<String> areaNames = new ArrayList<String>();
		
		List<Map<String, Object>> listall = this.gpsChianMapper
				.selectListAll();
		// data1 资金
		for (Map<String, Object> o : list) {
			Map<String, Object> e = new HashMap<String, Object>();
			e.put("name", o.get("area_name"));
			e.put("value", CommonUtils.getRandomNum(56, 698));
			data1.add(e);
			data2.add(e);
			areaNames.add((String) o.get("area_name"));
		}
		rst.put("data1", data1);
		rst.put("data2", data2);
		
		// data3
		for (Map<String, Object> o : listall) {
			List<Object> e = new ArrayList<Object>();
			e.add(o.get("gps_x"));
			e.add(o.get("gps_y"));
			data3.put((String) o.get("area_name"), e);
		}
		
		rst.put("data3", data3);
		 int[] temp=CommonUtils.getrandomarray((int)CommonUtils.getRandomNum(1,list.size()));
		for (int i : temp) {
			List<Object> e = new ArrayList<Object>();
			Map<String, Object> m1 = new HashMap<String, Object>();
			Map<String, Object> m2 = new HashMap<String, Object>();
			
			m1.put("name", areaNames.get(i));
			m1.put("value",CommonUtils.getRandomNum(8,36));
			m2.put("name", "贵阳市");
			e.add(m1);
			e.add(m2);
			data4.add(e);
			//this.logger.info(m1);
			//this.logger.info(m2);
			
		}
		rst.put("data4", data4);
		return rst;
	}
	public Object selectListByPAreaName99(GpsChianQVo condition) throws Exception {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Object> dateShow=new ArrayList<Object>();
		List<Map<String, Object>> list = this.gpsChianMapper.selectListByPAreaName99(condition.getAreaName());
		if(list==null||list.size()==0){
			dateShow.add(0);
			dateShow.add(0);
			dateShow.add(0);
			dateShow.add(0);
		}
        for (Map<String, Object> map : list) {
        	dateShow.add(map.get("count"));
        	dateShow.add(map.get("moneyAve"));
		 }
        rst.put("data", dateShow);
		return rst;
	}
}
