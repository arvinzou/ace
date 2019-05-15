package com.huacainfo.ace.cu.service.impl;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.cu.dao.CuDonateOrderDao;
import com.huacainfo.ace.cu.service.CuDayDonateService;
import com.huacainfo.ace.cu.vo.CuDonateOrderVo;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cuDayDonateService")
public class CuDayDonateServiceImpl implements CuDayDonateService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CuDonateOrderDao cuDonateOrderDao;

	public ResultResponse initDayDonateData(String userId, String projectId) {
		JSONObject data = new JSONObject();

		List<Map<String, Object>> bsList = this.cuDonateOrderDao.bulletScreenData(projectId,0, 50);
		data.put("bulletScreen", JSONArray.fromObject(bsList));

		Map<String, Object> totalMap = this.cuDonateOrderDao.getTotalMoney(projectId);
		data.put("dayAmount", totalMap.get("dayAmount"));
		data.put("totalAmount", totalMap.get("totalAmount"));

		List<CuDonateOrderVo> detailList = this.cuDonateOrderDao.getDayDonateDetails(projectId, userId);
		BigDecimal totalHeartPoints = new BigDecimal(0);
		int totalActionPoints = 0;
		BigDecimal todayPoints = new BigDecimal(0);
		int todayPoint = 0;
		for (CuDonateOrderVo vo : detailList) {
			totalHeartPoints = totalHeartPoints.add(vo.getDonateAmount());
			totalActionPoints += vo.getDayDonatePoint();
			if (DateUtils.isSameDay(vo.getDonateDate(), new Date())) {
				todayPoints = todayPoints.add(vo.getPoints());
				if (vo.getDayDonatePoint() == 1) {
					todayPoint = 1;
				}
			}
		}
		data.put("totalActionPoint", Integer.valueOf(totalActionPoints));
		data.put("todayActionPoint", Integer.valueOf(todayPoint));
		data.put("totalHeartPoint", Integer.valueOf(totalHeartPoints.intValue() / 10));
		data.put("todayHeartPoint", Integer.valueOf(todayPoints.intValue() / 10));

		return new ResultResponse(0, "获取初始化数据成功！", data.toString());
	}

	public ResultResponse personalDonateDataDetails(String userId, String projectId) {
		JSONArray data = new JSONArray();
		JSONObject obj = new JSONObject();
		List<CuDonateOrderVo> detailList = this.cuDonateOrderDao.getDayDonateDetails(projectId, userId);
		for (CuDonateOrderVo vo : detailList) {
			int amount = vo.getDonateAmount().intValue();
			obj.put("date", new SimpleDateFormat("yyyy-MM-dd").format(vo.getDonateDate()));
			obj.put("amount", Integer.valueOf(amount));
			obj.put("heartPoint", Integer.valueOf(amount / 10));
			obj.put("actionPoint", Integer.valueOf(vo.getDayDonatePoint()));
			data.add(obj);
		}
		return new ResultResponse(0, "获取个人捐赠明细数据成功！", data.toString());
	}

	public ResultResponse pointsRank(String userId,String projectId) {
		JSONObject data = new JSONObject();
		List<Map<String, Object>> totalPoints = this.cuDonateOrderDao.getTotalPointsRank(projectId);
		List<Map<String, Object>> actionPoints = this.cuDonateOrderDao.getActionPointsRank(projectId);
		List<Map<String, Object>> heartPoints = this.cuDonateOrderDao.getHeartPointsRank(projectId);

		JSONObject temp = new JSONObject();
		for (Map<String, Object> map : totalPoints) {
			if (userId.equals(map.get("unionId"))) {
				temp.put("userRank", JSONObject.fromObject(map));
				break;
			}
		}
		temp.put("rank", JSONArray.fromObject(totalPoints));
		data.put("totalRank", temp);

		for (Map<String, Object> map : actionPoints) {
			temp.clear();
			if (userId.equals(map.get("unionId"))) {
				temp.put("userRank", JSONObject.fromObject(map));
				break;
			}
		}
		temp.put("rank", JSONArray.fromObject(actionPoints));
		data.put("actionRank", temp);
		for (Map<String, Object> map : heartPoints) {
			temp.clear();
			if (userId.equals(map.get("unionId"))) {
				temp.put("userRank", JSONObject.fromObject(map));
				break;
			}
		}
		temp.put("rank", JSONArray.fromObject(heartPoints));
		data.put("heartRank", temp);

		return new ResultResponse(0, "获取积分排名信息成功！", data.toString());
	}
}
