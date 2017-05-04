package com.huacainfo.ace.operana.service.impl;

import java.util.*;

import com.huacainfo.ace.operana.dao.MeetingDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.NormDao;
import com.huacainfo.ace.operana.dao.NormDataDao;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.model.NormData;
import com.huacainfo.ace.operana.service.NormDataService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
@Service("normDataService")
public class NormDataServiceImpl implements NormDataService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormDataDao normDataDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private NormDao normDao;

	@Autowired
	private MeetingDao meetingDao;

	private List<Integer> wk = new ArrayList<Integer>();

	public NormDataServiceImpl() {
		for (int i = 1; i < 54; i++) {
			wk.add(i);
		}
	}
	public MessageResponse deleteNormDataByNormDataId(String id, UserProp userProp) throws Exception {
		this.normDataDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除运营数据", "运营数据", String.valueOf(id), String.valueOf(id), "运营数据", userProp);
		return new MessageResponse(0, "运营数据删除完成！");
	}

	public MessageResponse importXls(List<Map<String, Object>> list, Map<String, Object> params,String sheetName, UserProp userProp)
			throws Exception {
		int i = 1;
		for (Map<String, Object> row : list) {
			NormData o = new NormData();
			o.setNormId((String)row.get("normId"));
			if(CommonUtils.isNotEmpty(row.get("lastYear"))){
				o.setLastYear(new java.math.BigDecimal((String)row.get("lastYear")));
			}

			CommonBeanUtils.copyMap2Bean(o, params);
			Calendar a=Calendar.getInstance();
			int year=a.get(Calendar.YEAR);
			o.setYear(year);
			this.logger.info(o.toString());

			if (CommonUtils.isBlank(o.getMeetingId())) {
				return new MessageResponse(1, sheetName+"第" + i + "行会议编码不能为空！");
			}
			if (CommonUtils.isBlank(o.getTopicId())) {
				return new MessageResponse(1, sheetName+"第" + i + "行议题编码不能为空！");
			}
			if (CommonUtils.isBlank(o.getNormId())) {
				return new MessageResponse(1, sheetName+"第" + i + "行指标编码不能为空！");
			}
			if (CommonUtils.isBlank(o.getYear())) {
				return new MessageResponse(1, sheetName+"第" + i + "行年度不能为空！");
			}
			if (CommonUtils.isBlank(o.getLastYear())) {
				//return new MessageResponse(1, sheetName+"第" + i + "上年度指标不能为空！");
			}
			Norm p = normDao.selectByPrimaryKey(o.getNormId());
			if (CommonUtils.isBlank(p)) {
				return new MessageResponse(1, sheetName+"第" + i + "指标编码不存在！");
			}
			Map<String, Object> t = new HashMap<String, Object>();
			if (p.getCalType().equals("1")) {
				for (int k : wk) {
					if(!CommonUtils.isBlank(row.get("wk" + k))){
						if (!CommonUtils.isNumber((String) row.get("wk" + k))) {
							return new MessageResponse(1, sheetName+"第" + i + "行wk" + k + "数据格式不正确，必须为数值！");
						}
						t.put("wkt" + k, new java.math.BigDecimal((String) row.get("wk" + k)));
					}

				}
			} else {
				for (int k : wk) {
					if(!CommonUtils.isBlank(row.get("wk" + k))){
						String e = (String) row.get("wk" + k);
						if (e.indexOf("/") == -1) {
							return new MessageResponse(1, sheetName+"第" + i + "行wk" + k + "数据格式不正确，必须为'数字/数字'！");
						}
						if (!CommonUtils.isNumber((String) e.split("/")[0])) {
							return new MessageResponse(1, sheetName+"第" + i + "行wk" + k + "数据格式不正确，必须为数值！");
						}
						if (!CommonUtils.isNumber((String) e.split("/")[1])) {
							return new MessageResponse(1, sheetName+"第" + i + "行wk" + k + "数据格式不正确，必须为数值！");
						}
						t.put("wkc" + k, new java.math.BigDecimal(e.split("/")[0]));
						t.put("wkt" + k, new java.math.BigDecimal(e.split("/")[1]));
					}

				}
			}
			this.logger.info("----------->",t);
			CommonBeanUtils.copyMap2Bean(o, t);
			int m = normDataDao.isExit(o);
			if (m > 0) {
				o.setLastModifyDate(new Date());
				o.setLastModifyUserName(userProp.getName());
				o.setLastModifyUserId(userProp.getUserId());
				this.normDataDao.updateByPrimaryKey(o);
			} else {
				o.setId(UUID.randomUUID().toString());
				o.setCreateDate(new Date());
				o.setCreateUserName(userProp.getName());
				o.setCreateUserId(userProp.getUserId());
				this.normDataDao.insert(o);
			}
			i++;
		}
		this.dataBaseLogService.log("指标数据导入", "指标数据", "", "rs.xls", "rs.xls", userProp);
		return new MessageResponse(0, "导入完成！");
	}
	public List<Map<String, Object>> selectNormByMeetingAndTopicId(String meetingId,String topicId) throws Exception {

		return this.normDataDao.selectNormByMeetingAndTopicId(meetingId,topicId);
	}
	public List<Map<String,Object>> selectTopicByMeetingId(String meetingId) throws Exception{
		return  this.meetingDao.selectTopicByMeetingId(meetingId);
	}
	public Map<String,String> selectTopicDictByMeetingId(String meetingId) throws Exception{
		Map<String,String> rst=new HashMap<String,String>();
		List<Map<String, Object>> list= this.meetingDao.selectTopicByMeetingId(meetingId);
		for(Map<String, Object> o:list){
			rst.put((String)o.get("name"),(String)o.get("id"));
		}
		return rst;
	}
}
