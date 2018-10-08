package com.huacainfo.ace.cu.service.impl;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.cu.dao.report.ReportDao;
import com.huacainfo.ace.cu.service.AnalysisService;
import com.huacainfo.ace.cu.service.CuUserService;
import com.huacainfo.ace.cu.vo.CuUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);
    @Autowired
    private CuUserService cuUserService;

    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition,
                                                 String reportId, int start, int limit) throws Exception {
//		ListResult<Map<String, Object>> rst = new ListResult<Map<String, Object>>();
//		List<Map<String, Object>> p=new ArrayList<Map<String, Object>>();
//		ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
//		condition.put("start",start);
//		condition.put("limit",limit);
//		List<Map<String, Object>> list=dao.query(condition);
//		for(Map<String, Object> e:list){
//			p.add(CommonUtils.sortMapByKey(e));
//		}
//		rst.setValue(p);
//		return rst;
        return null;
    }

    /**
     * 财务公开数额统计
     *
     * @param projectId
     * @returnf
     */
    @Override
    public Map<String, Object> financeStatistics(String projectId) {
        ReportDao dao = (ReportDao) SpringUtils.getBean("financeStatistics");

        Map<String, Object> condition = new HashMap<>();
        if (!CommonUtils.isEmpty(projectId)) {
            condition.put("ids", projectId.split(","));
        }

        List<Map<String, Object>> mapList = dao.query(condition);

        return CollectionUtils.isEmpty(mapList) ? null : mapList.get(0);
    }

    /**
     * 慈善榜单
     *
     * @param projectId 项目ID
     * @param openId    微信openid
     * @param start     页码
     * @param limit     页数
     * @param orderBy
     * @return
     */
    @Override
    public List<Map<String, Object>> donateRank(String projectId, String openId, int start, int limit, String orderBy) {
        ReportDao dao = (ReportDao) SpringUtils.getBean("donateRank");

        Map<String, Object> condition = new HashMap<>();
        condition.put("start", start);
        condition.put("limit", limit);
        condition.put("orderBy", orderBy);
        condition.put("appid", PropertyUtil.getProperty("appid"));
        if (StringUtil.isNotEmpty(projectId)) {
            condition.put("projectId", projectId);
        }
        if (StringUtil.isNotEmpty(openId)) {
            condition.put("openId", openId);
        }

        List<Map<String, Object>> mapList = dao.query(condition);
        return mapList;
    }

    /**
     * 慈善榜单 - 2
     *
     * @param projectId
     * @param needOpenId
     * @param openId
     * @param start
     * @param limit
     * @param orderBy
     */
    @Override
    public Map<String, Object> donateRank(String projectId, String needOpenId, String openId,
                                          int start, int limit, String orderBy) {
        Map<String, Object> respMap = new HashMap<>();
        //数据查询
        List<Map<String, Object>> list = donateRank(projectId, "", start, limit, orderBy);
        if ("1".equals(needOpenId) && StringUtil.isNotEmpty(openId)) {
            //筛选过滤
            Map<String, Object> ownData = new HashMap<>();
            List<Map<String, Object>> removeList = new ArrayList<>();
            for (Map<String, Object> map : list) {
                if (openId.equals(String.valueOf(map.get("openid")))) {
                    ownData.putAll(map);
//                        removeList.add(map);
                }
            }
//                list.removeAll(removeList);
            //第一次查询集合不包含自己时，查询指定openid一次看是否有相关结果
            if (ownData.size() == 0) {
                List<Map<String, Object>> ownList = donateRank(projectId, openId, start, limit, orderBy);
                ownData = CollectionUtils.isEmpty(ownList) ? null : ownList.get(0);
            }
            respMap.put("own", null == ownData ? getDefaultOwnData(openId) : ownData);
        } else {
            respMap.put("own", null);
        }


        respMap.put("list", list);
        return respMap;
    }

    private Map<String, Object> getDefaultOwnData(String openId) {
        CuUserVo vo = cuUserService.findByOpenId(openId);

        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("nickname", null == vo ? "-" : vo.getNickname());
        rtnMap.put("headimgurl", null == vo ? "-" : vo.getHeadimgurl());
        rtnMap.put("openid", null == vo ? "-" : vo.getOpenId());
        rtnMap.put("totalDonateAmount", 0);
        rtnMap.put("donateCount", 0);
        rtnMap.put("donateDays", 0);

        return rtnMap;
    }


}
