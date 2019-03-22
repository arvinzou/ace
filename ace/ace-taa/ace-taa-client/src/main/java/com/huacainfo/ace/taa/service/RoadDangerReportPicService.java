package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.taa.model.RoadDangerReportPic;
import com.huacainfo.ace.taa.vo.RoadDangerReportPicVo;
import com.huacainfo.ace.taa.vo.RoadDangerReportPicQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: 何双
 * @version: 2019-03-15
 * @Description: TODO(路况上报)
 */
public interface RoadDangerReportPicService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(路况上报分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RordDangerReportPicVo>
     * @author: 何双
     * @version: 2019-03-15
     */
    PageResult<RoadDangerReportPicVo> findRordDangerReportPicList(RoadDangerReportPicQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertRordDangerReportPic
     * @Description: TODO(添加路况上报)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse insertRordDangerReportPic(RoadDangerReportPic obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRordDangerReportPic
     * @Description: TODO(更新路况上报)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse updateRordDangerReportPic(RoadDangerReportPic obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRordDangerReportPicByPrimaryKey
     * @Description: TODO(获取路况上报)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<RordDangerReportPic>
     * @author: 何双
     * @version: 2019-03-15
     */
    SingleResult<RoadDangerReportPicVo> selectRordDangerReportPicByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRordDangerReportPicByRordDangerReportPicId
     * @Description: TODO(删除路况上报)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse deleteRordDangerReportPicByRordDangerReportPicId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核路况上报)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
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
     * @author: 何双
     * @version: 2019-03-15
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map < String, Object>>
     * @author: 何双
     * @version: 2019-03-15
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: 何双
     * @version: 2019-03-15
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除路况上报 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    public MessageResponse deleteRordDangerReportPicByRordDangerReportPicIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 何双
     * @version: 2019-03-15
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 获取未整路患现场照片
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<RoadDangerReportPic> selectNochangedMethodImg(String id) throws Exception;

    /**
     * 获取整措施后照片
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<RoadDangerReportPic> selectChangedMethodImg(String id) throws Exception;
}
