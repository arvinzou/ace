package com.huacainfo.ace.fop.service.impl;

import com.huacainfo.ace.common.plugins.wechat.api.WeChatApi;
import com.huacainfo.ace.common.plugins.wechat.entity.UserList;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.fop.dao.report.Portal;
import com.huacainfo.ace.fop.dao.report.ReportDao;
import com.huacainfo.ace.fop.service.AnalysisService;
import com.huacainfo.ace.portal.service.WxCfgService;
import com.huacainfo.ace.portal.vo.WxCfgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("analysisService")
public class AnslysisServiceImpl implements AnalysisService {
    private static final Logger logger = LoggerFactory.getLogger(AnslysisServiceImpl.class);

    @Autowired
    private Portal portal;
    @Autowired
    private WxCfgService wxCfgService;

    @Override
    public ListResult<Map<String, Object>> query(Map<String, Object> condition,
                                                 String reportId, int start, int limit) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        ReportDao dao = (ReportDao) SpringUtils.getBean(reportId);
        condition.put("start", start);
        condition.put("limit", limit);
        List<Map<String, Object>> list = dao.query(condition);

        List<Map<String, Object>> p = new ArrayList<>();
//        if ("memberArea".equals(reportId)) {
        p = parseMemberAreaLData(p, list);
//        } else {
//            for (Map<String, Object> e : list) {
//                p.add(CommonUtils.sortMapByKey(e));
//            }
//        }

        rst.setValue(p);
        return rst;
    }

    /**
     * 会员分布数据转换
     *
     * @param p
     * @param list
     * @return
     */
    private List<Map<String, Object>> parseMemberAreaLData(List<Map<String, Object>> p,
                                                           List<Map<String, Object>> list) {
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> e : list) {
            map.put((String) e.get("name"), e.get("value"));
            p.add(map);
        }

        return p;
    }

    /**
     * portal页顶端数据统计
     *
     * @return
     */
    @Override
    public Map<String, Object> portalCount() throws Exception {

        Map<String, Object> rtnMap = portal.portalCount();
        //公众号关注人数
        SingleResult<WxCfgVo> s = wxCfgService.selectWxCfgByPrimaryKey(PropertyUtil.getProperty("appid"));
        WxCfgVo cfg = s == null ? null : s.getValue();
        UserList u = null == cfg ? null : WeChatApi.getUserList(cfg.getAccessToken(), "");
        rtnMap.put("paCount", u == null ? 0 : u.getTotal());

        return rtnMap;
    }
}
