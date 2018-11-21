package com.huacainfo.ace.live.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.live.model.Live;
import com.huacainfo.ace.live.vo.LiveQVo;
import com.huacainfo.ace.live.vo.LiveVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description: TODO(直播)
 */
public interface WWWService {
    /**
     * @throws
     * @Title:getLiveList
     * @Description: TODO(微网页获取直播列表)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    Map<String, Object> getLiveList(String companyId, int page, Map<String, Object> p);

    /**
     * @throws
     * @Title:getLive
     * @Description: TODO(微网页根据直播获取ID，直播信息信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-01
     */
    Map<String, Object> getLive(Map<String, Object> p);

    /**
     * @throws
     * @Title:getLiveSubList
     * @Description: TODO(微网页根据直播间RID获取图文直播内容)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    Map<String, Object> getLiveRptList(String rid, int page, Map<String, Object> p);

    /**
     * @throws
     * @Title:getLiveMsgList
     * @Description: TODO(微网页根据直播间RID获取互动内容)
     * @param: @param p
     * @param: @throws Exception
     * @return: List<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2018-01-07
     */
    List<Map<String, Object>> getLiveMsgList(Map<String, Object> p);


    /**
     * @throws
     * @Title:getTotalNumAndOrgInfo
     * @Description: TODO(微网页根据单位代码直播代码获取合计直播信息)
     * @param: @param deptId
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-09
     */
    Map<String, Object> getTotalNumAndOrgInfo(String deptId, String id);


    /**
     * @throws
     * @Title:getTotalPageAndOrgInfo
     * @Description: TODO(微网页根据单位代码获取合计直播信息)
     * @param: @param deptId
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-09
     */
    Map<String, Object> getTotalPageAndOrgInfo(String deptId);

    /**
     * @throws
     * @Title:getShareContent
     * @Description: TODO(微网页根据单位代码获取分享信息)
     * @param: @param deptId
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-09
     */
    Map<String, Object> getShareContent(String deptId,String fastdfs_server);


    /**
     * @throws
     * @Title:updateRptLikeNum
     * @Description: TODO(微网页报道点赞)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-14
     */
    Map<String, Object> updateRptLikeNum(String id, String type);

    /**
     * @throws
     * @Title:updateRptVisitNum
     * @Description: TODO(微网页浏览人次)
     * @param: @param id
     * @param: @throws Exception
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2018-01-14
     */
    Map<String, Object> updateRptVisitNum(String id);
}
