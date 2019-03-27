package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.partyschool.model.AttRecord;
import com.huacainfo.ace.partyschool.vo.AttRecordExport;
import com.huacainfo.ace.partyschool.vo.AttRecordQVo;
import com.huacainfo.ace.partyschool.vo.AttRecordVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-02-20
 * @Description: TODO(学员考勤)
 */
public interface AttRecordService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(学员考勤分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AttRecordVo>
     * @author: Arvin
     * @version: 2019-02-20
     */
    PageResult<AttRecordVo> findAttRecordList(AttRecordQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertAttRecord
     * @Description: TODO(添加学员考勤)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse insertAttRecord(AttRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateAttRecord
     * @Description: TODO(更新学员考勤)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse updateAttRecord(AttRecord obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectAttRecordByPrimaryKey
     * @Description: TODO(获取学员考勤)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AttRecord>
     * @author: Arvin
     * @version: 2019-02-20
     */
    SingleResult<AttRecordVo> selectAttRecordByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteAttRecordByAttRecordId
     * @Description: TODO(删除学员考勤)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse deleteAttRecordByAttRecordId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核学员考勤)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
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
     * @author: Arvin
     * @version: 2019-02-20
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
     * @author: Arvin
     * @version: 2019-02-20
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String,Object>
     * @author: Arvin
     * @version: 2019-02-20
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除学员考勤）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    public MessageResponse deleteAttRecordByAttRecordIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-02-20
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 导入中控数据
     *
     * @param fileName db文件
     * @param nowTime  当前操作时间
     * @return MessageResponse
     */
    MessageResponse saveZkData(String fileName, String nowTime);

    /**
     * 查询登录用户考勤信息 -- 查询某一天的考勤数据
     *
     * @param dateTimeStr 日期字符串 ，包含年月日；示例： 2019-02-21
     * @return ResultResponse
     */
    ResultResponse findList(String userId, String dateTimeStr);

    List<AttRecordExport> exportAttRecord(AttRecordQVo condition);
}
