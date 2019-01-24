package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.taa.model.TraAcc;
import com.huacainfo.ace.taa.vo.TraAccQVo;
import com.huacainfo.ace.taa.vo.TraAccVo;

import java.util.List;
import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2019-01-10
 * @Description: TODO(事故)
 */
public interface TraAccService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(事故分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TraAccVo>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    PageResult<TraAccVo> findTraAccList(TraAccQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTraAcc
     * @Description: TODO(添加事故)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse insertTraAcc(TraAcc obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTraAcc
     * @Description: TODO(更新事故)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse updateTraAcc(TraAcc obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTraAccByPrimaryKey
     * @Description: TODO(获取事故)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TraAcc>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    SingleResult<TraAccVo> selectTraAccByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTraAccByTraAccId
     * @Description: TODO(删除事故)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse deleteTraAccByTraAccId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核事故)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:importXls
     * @Description: TODO(Excel导入资源数据)
     * @param: @param list
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除事故
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    public MessageResponse deleteTraAccByTraAccIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-10
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 功能描述: 事故快报
     *
     * @param: data 上报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 10:57
     */
    ResultResponse flashReport(WxUser user, TraAccVo params) throws Exception;

    /**
     * 功能描述: 事故续报
     *
     * @param user   用户信息
     * @param params 续报参数
     * @return: ResultResponse
     * @auther: Arvin Zou
     * @date: 2019/1/12 11:15
     */
    ResultResponse report(WxUser user, TraAccVo params);

    /**
     * 交通事故倒序表
     * <p>路段交通事故次数倒叙表</p>
     * <p>路段交通死亡人数倒叙表</p>
     *
     * @param params  参数
     * @param start   分页1
     * @param limit   分页2
     * @param orderBy 排序规则
     *                ORDER BY v.occurTimes DESC
     *                ORDER BY v.deathNum DESC
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> reverseReport(Map<String, Object> params,
                                            int start,
                                            int limit,
                                            String orderBy);

    /**
     * 事故死亡人数同期对比 报表
     *
     * @param params params
     * @return Map<String,Object>
     */
    Map<String, Object> contrastiveReport(Map<String, String> params);


    /**
     * @throws
     * @Title:getLatLongByAreaCode
     * @Description: TODO(获取行政区划中心坐标)
     * @param: @param areaCode 行政区划编码
     * @param: @throws Exception
     * @return: SingleResult
     * @author: 陈晓克
     * @version: 2019-01-19
     */
    SingleResult<Map<String, Object>> getLatLongByAreaCode(String areaCode) throws Exception;

    /**
     * @throws
     * @Title:getTraAccListTx
     * @Description: TODO(交通事故热力图)
     * @param: @param condition
     * @param: @throws Exception
     * @return: List<Map<String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-21
     */
    List<Map<String, Object>> getTraAccListTx(TraAccQVo condition) throws Exception;

    /**
     * @throws
     * @Title:getTraAccListTd
     * @Description: TODO(交通事故热力图)
     * @param: @param condition
     * @param: @throws Exception
     * @return: List<Map<String, Object>>
     * @author: 陈晓克
     * @version: 2019-01-21
     */
    List<Map<String, Object>> getTraAccListBd(TraAccQVo condition) throws Exception;

    /**
     * 掌上驾驶仓
     *
     * @param areaCode    行政区划
     * @param dateTimeStr 查询年月;7位有效数据，默认当前年月
     * @return Map<String, Object>
     */
    Map<String, Object> multipleReport(String areaCode, String dateTimeStr);

    /**
     * 查询行政区划列表
     *
     * @return Map<String, Object>
     */
    List<Map<String, Object>> findDistrictList(String areaCode);

    /**
     * 掌上驾驶仓 - 事故柱形图
     *
     * @param category    查询类型 times-事故次数 ； death-死亡人数
     * @param dateTimeStr 查询年月;7位有效数据，默认当前年月
     * @return Map<String, Object>
     */
    List<Map<String, Object>> histogramReport(String category, String dateTimeStr);

    /**
     * 事故分析 报表
     *
     * @param category      查询类型 按年-year, 按季度-season, 按月-month
     * @param dateTimeStr   时间字符串
     * @param roadManId     路长ID
     * @param roadSectionId 路段ID
     * @param field         统计字段 deadthToll ,injuries
     * @return Map<String,Object>
     */
    List<Map<String, Object>> analysisReport(String category, String dateTimeStr,
                                             String roadManId, String roadSectionId, String field);
}
