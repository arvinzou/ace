package com.huacainfo.ace.operana.service.impl;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.ChartDao;
import com.huacainfo.ace.operana.dao.MeetingDao;
import com.huacainfo.ace.operana.dao.NormDao;
import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.service.ChartService;
import com.huacainfo.ace.operana.service.common.DataCalUtils;
/**
 * Created by chenxiaoke on 2017/5/8.
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ChartServiceImpl.class);
    @Autowired
    private ChartDao chartDao;

    @Autowired
    private NormDao normDao;

	@Autowired
	private MeetingDao meetingDao;

	private List<Integer> wk = new ArrayList<Integer>();

	public ChartServiceImpl(){
		for (int i = 1; i < 54; i++) {
			wk.add(i);
		}
	}
	@Override
	public Map<String,Object> chart1(String meetingId,String topicId,String normId,String viewType) throws Exception {
		Map<String,Object> rst=new HashMap<String,Object>();
        Calendar a=Calendar.getInstance();
        int year=a.get(Calendar.YEAR);
        Norm norm=normDao.selectByPrimaryKey(normId);
        Meeting meeting=this.meetingDao.selectByPrimaryKey(meetingId);
        Map<String,java.math.BigDecimal> row=this.chartDao.selectNormDataByMeetingIdTopicIdNormId(meetingId,topicId,normId);
        logger.info("{}",row);
        List<String> x=new ArrayList<String>();
		List<java.math.BigDecimal> y=new ArrayList<java.math.BigDecimal>();
		List<java.math.BigDecimal> index=new ArrayList<java.math.BigDecimal>();
        int lastYear=year-1;
        int cwk=meeting.getCvalue();
        String calType=norm.getCalType();
        java.math.BigDecimal normIndex=this.chartDao.selectNormIndex(year,normId);

		Map<String,java.math.BigDecimal> normIndexMap=this.chartDao.selectNormIndexMap(year,normId);

        if(CommonUtils.isBlank(normIndex)){
			normIndex=new java.math.BigDecimal("0");
		}
		logger.info("cwk->{}",cwk);
		logger.info("calType->{}",calType);
        List<Integer> quarters=new ArrayList<Integer>();
		List<Integer> months=new ArrayList<Integer>();
		List<Integer> weeks=new ArrayList<Integer>();
		DataCalUtils.calX(cwk,quarters,months,weeks,viewType);
		logger.info("quarters->{}",quarters);
		logger.info("months->{}",months);
		logger.info("weeks->{}",weeks);
		x.add(lastYear+"年");
		y.add(row.get("lastYear"));
		index.add(normIndex);
		for(Integer e:quarters){
			x.add(e+"季度");
			y.add(DataCalUtils.calQuarterY(e,row,calType));
			normIndex=DataCalUtils.getNormInex(normIndexMap,e,"quarters",normIndex);
			index.add(normIndex);
		}
		for(Integer e:months){
			x.add(e+"月");
			y.add(DataCalUtils.calMonthY(e,row,calType));
			normIndex=DataCalUtils.getNormInex(normIndexMap,e,"months",normIndex);
			index.add(normIndex);
		}
		for(Integer e:weeks){
			x.add(e+"周");
			y.add(DataCalUtils.calWeekY(e,row,calType));
			index.add(normIndex);
		}
		x.add(year+"年累计");
		y.add(DataCalUtils.calYearY(cwk,row,calType));
		index.add(normIndex);
		rst.put("dataX",x);
		rst.put("dataY",y);
		rst.put("index",index);
		rst.put("name",norm.getName());
		rst.put("calType",calType);
		rst.put("xAxis",year+"年累计");
		rst.put("yAxis",normIndex);
		return rst;
	}





	@Override
	public Map<String,Object> chart2(String meetingId,String topicId,String normId) throws Exception {
		Map<String,Object> rst=new HashMap<String,Object>();
		Norm norm=normDao.selectByPrimaryKey(normId);
		Meeting meeting=this.meetingDao.selectByPrimaryKey(meetingId);
		int cwk=meeting.getCvalue();
		List<Map<String,Object>> list=this.chartDao.selectTop10Problem(meetingId,topicId,normId,String.valueOf(cwk));
		List<String> dataX=new ArrayList<String>();
		List<java.math.BigDecimal> dataY1=new ArrayList<java.math.BigDecimal>();
		List<java.math.BigDecimal> dataY2=new ArrayList<java.math.BigDecimal>();
		java.math.BigDecimal total=new java.math.BigDecimal(0);
		for(Map<String,Object> o:list){
			java.math.BigDecimal e=(java.math.BigDecimal) o.get("values");
			total=total.add(e);
		}
		java.math.BigDecimal c=new java.math.BigDecimal(0);
		int i=0;
		for(Map<String,Object> o:list){
			i++;
			java.math.BigDecimal e=(java.math.BigDecimal) o.get("values");
			dataX.add((String) o.get("name"));
			dataY1.add(e);
			c=c.add(e.divide(total,4,java.math.BigDecimal.ROUND_HALF_EVEN));
			if(i==list.size()){
				dataY2.add(new java.math.BigDecimal(100));
			}else{
				dataY2.add(c.multiply(new java.math.BigDecimal(100)));
			}

		}
		rst.put("dataX",dataX);
		rst.put("dataY1",dataY1);
		rst.put("dataY2",dataY2);
		return rst;
	}

	@Override
	public Map<String,Object> chart3(String meetingId,String topicId,String normId) throws Exception {
		Meeting meeting=this.meetingDao.selectByPrimaryKey(meetingId);
		int cwk=meeting.getCvalue();
		List<String> item=new ArrayList<String>();
		List<String> itemName=new ArrayList<String>();
		Map<String,Object> rst=new HashMap<String,Object>();
		int a=cwk-12;
		if(a<=0){
			a=1;
		}
		item.add("name");
		itemName.add("不良现象");
		for(int i=a;i<=cwk;i++){
			item.add("wk"+i);
			itemName.add("WK"+i);
		}
		List<Map<String,Object>> e=this.chartDao.selectTop10ProblemDetail(meetingId,topicId,normId,String.valueOf(cwk),item);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();

		for(Map<String,Object> o:e){
			java.math.BigDecimal total=new java.math.BigDecimal(0);
			for(String m:item){
				if(!m.equals("name")){
					java.math.BigDecimal s=(java.math.BigDecimal)o.get(m);
					total=total.add(s);
				}

			}
			o.put("total",total);
			list.add(o);
		}
		item.add("total");
		itemName.add("累计");
		rst.put("item",item);
		rst.put("itemName",itemName);
		rst.put("data",list);
		rst.put("cwk","wk"+cwk);
		return rst;
	}

	@Override
	public Map<String,Object> chart4(String meetingId,String topicId,String normId) throws Exception {
		Meeting meeting=this.meetingDao.selectByPrimaryKey(meetingId);
		int cwk=meeting.getCvalue();
		List<String> item=new ArrayList<String>();
		List<String> itemName=new ArrayList<String>();
		List<Map<String,Object>> e=this.chartDao.selectTpa(meetingId,topicId,normId,"wk"+cwk);
		Map<String,Object> rst=new HashMap<String,Object>();
		item.add("probDiscri");
		itemName.add("问题");

		item.add("productId");
		itemName.add("产品编码");

		item.add("probAnsys");
		itemName.add("原因分析");

		item.add("actions");
		itemName.add("改善措施");

		item.add("userName");
		itemName.add("责任人");

		item.add("lastModifyDate");
		itemName.add("完成日期");

		item.add("status");
		itemName.add("状态");

		rst.put("data",e);
		rst.put("item",item);
		rst.put("itemName",itemName);
		rst.put("cwk","wk"+cwk);
		return rst;
	}




}
