package com.huacainfo.ace.operana.service.impl;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.service.ChartService;
import net.sf.json.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.operana.dao.ChartDao;
import com.huacainfo.ace.operana.dao.NormDao;
import com.huacainfo.ace.operana.dao.MeetingDao;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.model.Meeting;

import java.util.*;

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

	private static final double fl=4.3482;

	public ChartServiceImpl(){
		for (int i = 1; i < 54; i++) {
			wk.add(i);
		}
	}
	@Override
	public Map<String,Object> chart1(String meetingId,String topicId,String normId) throws Exception {
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
        if(CommonUtils.isBlank(normIndex)){
			normIndex=new java.math.BigDecimal("0");
		}
		logger.info("cwk->{}",cwk);
		logger.info("calType->{}",calType);
        List<Integer> quarters=new ArrayList<Integer>();
		List<Integer> months=new ArrayList<Integer>();
		List<Integer> weeks=new ArrayList<Integer>();
		this.calX(cwk,quarters,months,weeks);
		logger.info("quarters->{}",quarters);
		logger.info("months->{}",months);
		logger.info("weeks->{}",weeks);

		x.add(lastYear+"年");
		y.add(row.get("lastYear"));
		index.add(normIndex);
		for(Integer e:quarters){
			x.add(e+"季度");
			y.add(calQuarterY(e,row,calType));
			index.add(normIndex);
		}
		for(Integer e:months){
			x.add(e+"月");
			y.add(calMonthY(e,row,calType));
			index.add(normIndex);
		}
		for(Integer e:weeks){
			x.add(e+"周");
			y.add(calWeekY(e,row,calType));
			index.add(normIndex);
		}
		x.add(year+"年累计");
		y.add(calYearY(cwk,row,calType));
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



	private static java.math.BigDecimal calQuarterY(int quarter,Map<String,java.math.BigDecimal> row,String calType){
		java.math.BigDecimal rst=new java.math.BigDecimal("0");
		//logger.info("->row {}",row);
		if(calType.equals("1")){
			for(int i=((quarter*13)-13+1);i<=quarter*13;i++){
				logger.info("->{}",i);
				rst=rst.add(row.get("wkt"+i));
			}
		}else {
			java.math.BigDecimal wkt=new java.math.BigDecimal("0");
			java.math.BigDecimal wkc=new java.math.BigDecimal("0");
			for(int i=((quarter*13)-13+1);i<=quarter*13;i++){
				logger.info("-----i->{}",i);
				wkt=wkt.add(row.get("wkt"+i));
				wkc=wkc.add(row.get("wkc"+i));

			}
			logger.info("-----wkc->{}",wkc);
			logger.info("-----wkt->{}",wkt);
			rst=wkc.divide(wkt,4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}

	private static java.math.BigDecimal calMonthY(int month,Map<String,java.math.BigDecimal> row,String calType){
		java.math.BigDecimal rst=new java.math.BigDecimal("0");
		//logger.info("->row {}",row);
		if(calType.equals("1")){
			for(int i=(int)((month*fl)-fl+1);i<=month*fl;i++){
				logger.info("->{}",i);
				rst=rst.add(row.get("wkt"+i));
			}
		}else {
			java.math.BigDecimal wkt=new java.math.BigDecimal("0");
			java.math.BigDecimal wkc=new java.math.BigDecimal("0");
			for(int i=(int)((month*fl)-fl+1);i<=month*fl;i++){
				wkt=wkt.add(row.get("wkt"+i));
				wkc=wkc.add(row.get("wkc"+i));
			}
			rst=wkc.divide(wkt,4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}
	private static java.math.BigDecimal calWeekY(int cwk,Map<String,java.math.BigDecimal> row,String calType){
		java.math.BigDecimal rst=new java.math.BigDecimal("0");
		//logger.info("->row {}",row);
		if(calType.equals("1")){
			rst=rst.add(row.get("wkt"+cwk));
		}else {
			java.math.BigDecimal wkt=new java.math.BigDecimal("0");
			java.math.BigDecimal wkc=new java.math.BigDecimal("0");
			wkt=wkt.add(row.get("wkt"+cwk));
			wkc=wkc.add(row.get("wkc"+cwk));
			rst=wkc.divide(wkt,4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}
	private static java.math.BigDecimal calYearY(int cwk,Map<String,java.math.BigDecimal> row,String calType){
		java.math.BigDecimal rst=new java.math.BigDecimal("0");
		//logger.info("->row {}",row);
		if(calType.equals("1")){
			for(int i=1;i<=cwk;i++){
				logger.info("->{}",i);
				rst=rst.add(row.get("wkt"+i));
			}
		}else {
			java.math.BigDecimal wkt=new java.math.BigDecimal("0");
			java.math.BigDecimal wkc=new java.math.BigDecimal("0");
			for(int i=1;i<=cwk;i++){
				wkt=wkt.add(row.get("wkt"+i));
				wkc=wkc.add(row.get("wkc"+i));
			}
			rst=wkc.divide(wkt,4, java.math.BigDecimal.ROUND_HALF_EVEN);
		}
		return rst;
	}

	private static void calX(int cwk,List<Integer> quarters,List<Integer> months,List<Integer> weeks){
		int wkl=0;
		int month=(int)(cwk/fl);
		wkl=cwk-(int)(month*fl);
		int jd=0;
		if(month>=3){
			jd=month/3;
			month=month%3;
		}
		if(jd>0){
			for(int i=1;i<=jd;i++){
				quarters.add(i);
				logger.info("{}季度",i);
			}
		}
		if(month>0){
			for(int j=1;j<=month;j++){
				months.add((jd*3+j));
				logger.info("{}月",(jd*3+j));
			}
		}
		if(wkl>0){
			for(int k=(cwk-wkl+1);k<=cwk;k++){
				weeks.add(k);
				logger.info("第{}周",k);
			}
		}
	}

	@Override
	public Map<String,Object> chart2(String meetingId,String topicId,String normId) throws Exception {
		return null;
	}

	@Override
	public Map<String,Object> chart3(String meetingId,String topicId,String normId) throws Exception {
		return null;
	}

	@Override
	public Map<String,Object> chart4(String meetingId,String topicId,String normId) throws Exception {
		return null;
	}
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}
	public static void main(String args[]){
		Map<String,java.math.BigDecimal> row=new HashMap<String,java.math.BigDecimal>();
		row.put("lastYear",new java.math.BigDecimal("0.26"));
		for(int i=1;i<=53;i++){
			row.put("wkt"+i,new java.math.BigDecimal("100"));
			row.put("wkc"+i,new java.math.BigDecimal("10"));
		}
		/*System.out.println(calQuarterY(1,row,"1"));
		System.out.println(calQuarterY(2,row,"1"));
		System.out.println(calQuarterY(3,row,"1"));
		System.out.println(calQuarterY(4,row,"1"));
		System.out.println(calQuarterY(1,row,"2"));
		System.out.println(calQuarterY(2,row,"2"));
		System.out.println(calQuarterY(3,row,"2"));
		System.out.println(calQuarterY(4,row,"2"));*/

		//System.out.println(getWeekOfYear(new Date()));

		//calX(19,new ArrayList<Integer>(),new ArrayList<Integer>(),new ArrayList<Integer>());


		/*System.out.println(calMonthY(1,row,"1"));
		System.out.println(calMonthY(2,row,"1"));
		System.out.println(calMonthY(3,row,"1"));
		System.out.println(calMonthY(4,row,"1"));
		System.out.println(calMonthY(5,row,"1"));

		System.out.println(calMonthY(1,row,"2"));
		System.out.println(calMonthY(2,row,"2"));
		System.out.println(calMonthY(3,row,"2"));
		System.out.println(calMonthY(4,row,"2"));
		System.out.println(calMonthY(5,row,"2"));*/
		Map<String,Object> rst=new HashMap<String,Object>();
		int year=2017;
		List<String> x=new ArrayList<String>();
		List<java.math.BigDecimal> y=new ArrayList<java.math.BigDecimal>();
		int lastYear=year-1;
		int cwk=19;
		String calType="2";
		List<Integer> quarters=new ArrayList<Integer>();
		List<Integer> months=new ArrayList<Integer>();
		List<Integer> weeks=new ArrayList<Integer>();

		calX(cwk,quarters,months,weeks);
		x.add(lastYear+"年");
		y.add(row.get("lastYear"));
		for(Integer e:quarters){
			x.add(e+"季度");
			y.add(calQuarterY(e,row,calType));
		}
		for(Integer e:months){
			x.add(e+"月");
			y.add(calMonthY(e,row,calType));
		}
		for(Integer e:weeks){
			x.add(e+"周");
			y.add(calWeekY(e,row,calType));
		}
		x.add(year+"年累计");
		y.add(calYearY(cwk,row,calType));
		rst.put("dataX",x);
		rst.put("dataY",y);
		logger.info("{}", rst);
	}
}
