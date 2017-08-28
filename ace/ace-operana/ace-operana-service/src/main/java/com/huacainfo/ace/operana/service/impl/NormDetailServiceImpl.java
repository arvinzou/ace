package com.huacainfo.ace.operana.service.impl;

import java.math.BigDecimal;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.operana.dao.NormDao;
import com.huacainfo.ace.operana.dao.NormDetailDao;
import com.huacainfo.ace.operana.model.Norm;
import com.huacainfo.ace.operana.model.NormDetail;
import com.huacainfo.ace.operana.service.NormDetailService;
import com.huacainfo.ace.operana.vo.NormDetailQVo;
import com.huacainfo.ace.operana.vo.NormDetailVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("normDetailService")
public class NormDetailServiceImpl implements NormDetailService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormDetailDao normDetailDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private NormDao normDao;

	private List<Integer> wk = new ArrayList<Integer>();

	public NormDetailServiceImpl() {
		for (int i = 1; i < 54; i++) {
			wk.add(i);
		}
	}

	public MessageResponse deleteNormDetailByNormDetailId(String id, UserProp userProp) throws Exception {
		this.normDetailDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除异常明细", "异常明细", String.valueOf(id), String.valueOf(id), "异常明细", userProp);
		return new MessageResponse(0, "异常明细删除完成！");
	}

	public MessageResponse importXls(List<Map<String, Object>> list, Map<String, Object> params, String sheetName,
			UserProp userProp) throws Exception {
		int i = 1;
		for (Map<String, Object> row : list) {
			if (CommonUtils.isNotEmpty(row.get("name"))) {
				NormDetail o = new NormDetail();
				o.setNormId((String) row.get("normId"));
				o.setName((String) row.get("name"));
				o.setProductId((String) row.get("productId"));
				CommonBeanUtils.copyMap2Bean(o, params);
				Calendar a = Calendar.getInstance();
				int year = a.get(Calendar.YEAR);
				o.setYear(year);
				this.logger.info(o.toString());

				if (CommonUtils.isBlank(o.getMeetingId())) {
					return new MessageResponse(1, sheetName + "第" + i + "行会议编码不能为空！");
				}
				if (CommonUtils.isBlank(o.getTopicId())) {
					return new MessageResponse(1, sheetName + "第" + i + "行议题编码不能为空！");
				}
				if (CommonUtils.isBlank(o.getNormId())) {
					return new MessageResponse(1, sheetName + "第" + i + "行指标编码不能为空！");
				}
				if (CommonUtils.isBlank(o.getYear())) {
					return new MessageResponse(1, sheetName + "第" + i + "行年度不能为空！");
				}
				if (CommonUtils.isBlank(o.getName())) {
					 return new MessageResponse(1, sheetName+"第" + i +
					 "异常现象不能为空！");
				}
				Norm p = normDao.selectByPrimaryKey(o.getNormId());
				if (CommonUtils.isBlank(p)) {
					return new MessageResponse(1, sheetName + "第" + i + "指标编码不存在！");
				}
				Map<String, Object> t = new HashMap<String, Object>();
				for (int k : wk) {
					if (!CommonUtils.isBlank(row.get("wk" + k))) {
						if (!CommonUtils.isNumber((String) row.get("wk" + k))) {
							return new MessageResponse(1, sheetName + "第" + i + "行wk" + k + "数据格式不正确，必须为数值！");
						}
						t.put("wk" + k, new java.math.BigDecimal((String) row.get("wk" + k)));
					}
				}
				this.logger.info("----------->", t);
				CommonBeanUtils.copyMap2Bean(o, t);
				int m = normDetailDao.isExit(o);
				if (m > 0) {
					o.setLastModifyDate(new Date());
					o.setLastModifyUserName(userProp.getName());
					o.setLastModifyUserId(userProp.getUserId());
					this.normDetailDao.updateByPrimaryKey(o);
				} else {
					o.setId(UUID.randomUUID().toString());
					o.setCreateDate(new Date());
					o.setCreateUserName(userProp.getName());
					o.setCreateUserId(userProp.getUserId());
					this.normDetailDao.insert(o);
				}
				i++;
			}
		}
		this.dataBaseLogService.log("异常明细导入", "异常明细", "", "rs.xls", "rs.xls", userProp);
		return new MessageResponse(0, "导入完成！");
	}
	public List<Map<String, Object>> selectNormDetailByMeetingAndTopicIdAndNormId(String meetingId,String topicId,String normId) throws Exception {

		return this.normDetailDao.selectNormDetailByMeetingAndTopicIdAndNormId(meetingId, topicId,normId);
	}

	public Map<String, String> selectNormDictByTopicId(String topicId) throws Exception {
		Map<String, String> rst = new HashMap<String, String>();
		List<Map<String, Object>> list = this.normDao.selectNormByTopicId(topicId);
		for (Map<String, Object> o : list) {
			rst.put((String) o.get("name"), (String) o.get("id"));
		}
		return rst;
	}
	public  List<Map<String,Object>> selectNormByTopicId(String topicId) throws Exception{
		return this.normDao.selectNormByTopicId(topicId);
	}

	public PageResult<NormDetailVo> findNormDetailList(NormDetailQVo condition, int start, int limit, String orderBy)
			throws Exception {
		PageResult<NormDetailVo> rst = new PageResult<NormDetailVo>();
		List<NormDetailVo> list =new ArrayList<>();
		List<Map<String,Object>> e=this.normDetailDao.findList(condition, start, start + limit, orderBy);
		for(Map<String,Object> o:e){

			String cont="";
			Map<String,Object> tmp=new HashMap<String,Object>();
			for(int k:wk){
				cont="";
				BigDecimal t=(BigDecimal) o.get("wk"+k);
				if(!CommonUtils.isBlank(t)){
					cont=CommonUtils.getPrettyNumber(t.toString());
				}
				tmp.put("wk"+k,cont);
			}
			NormDetailVo vo=new NormDetailVo();
			vo.setId(UUID.randomUUID().toString());
			CommonBeanUtils.copyMap2Bean(vo,o);
			CommonBeanUtils.copyMap2Bean(vo,tmp);
			list.add(vo);
		}


		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.normDetailDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse saveOrUpdateNormDetail(NormDetailVo e, UserProp userProp) throws Exception {

		Map<String,Object> row=new HashMap<String,Object>();
		CommonBeanUtils.copyBean2Map(e,row);

		NormDetail o=new NormDetail();
		o.setId(UUID.randomUUID().toString());

		CommonBeanUtils.copyMap2Bean(o,row);
		Calendar a=Calendar.getInstance();
		int year=a.get(Calendar.YEAR);
		o.setYear(year);
		this.logger.info(o.toString());

		if (CommonUtils.isBlank(o.getMeetingId())) {
			return new MessageResponse(1, "会议编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getTopicId())) {
			return new MessageResponse(1, "议题编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getNormId())) {
			return new MessageResponse(1,"指标编码不能为空！");
		}
		if (CommonUtils.isBlank(o.getYear())) {
			return new MessageResponse(1, "年度不能为空！");
		}
		Map<String, Object> t = new HashMap<String, Object>();
		for (int k : wk) {
			if(!CommonUtils.isBlank(row.get("wk" + k))){
				if (!CommonUtils.isNumber((String) row.get("wk" + k))) {
					return new MessageResponse(1, "wk" + k + "数据格式不正确，必须为数值！");
				}
				t.put("wk" + k, new java.math.BigDecimal((String) row.get("wk" + k)));
			}

		}
		this.logger.info("----------->",t);
		CommonBeanUtils.copyMap2Bean(o, t);
		int temp = this.normDetailDao.isExit(o);
		if (temp > 0) {
			o.setLastModifyDate(new Date());
			o.setLastModifyUserName(userProp.getName());
			o.setLastModifyUserId(userProp.getUserId());
			this.normDetailDao.updateByPrimaryKey(o);
		}else{
			o.setCreateDate(new Date());
			o.setCreateUserName(userProp.getName());
			o.setCreateUserId(userProp.getUserId());
			this.normDetailDao.insert(o);
		}
		this.dataBaseLogService.log("更新数据", "会议", "", o.getId(), o.getId(), userProp);
		return new MessageResponse(0, "跟新数据完成！");
	}
}
