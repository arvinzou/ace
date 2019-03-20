package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.qyplugin.pojo.DeviceRst;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.policeschool.model.QyCrm;
import com.huacainfo.ace.policeschool.vo.QyCrmVo;
import com.huacainfo.ace.policeschool.vo.QyCrmQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: ArvinZou
 * @version: 2019-03-19
 * @Description: TODO(数据上传)
 */
public interface QyCrmService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(数据上传分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <QyCrmVo>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    PageResult<QyCrmVo> findQyCrmList(QyCrmQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertQyCrm
     * @Description: TODO(添加数据上传)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse insertQyCrm(QyCrm obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateQyCrm
     * @Description: TODO(更新数据上传)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse updateQyCrm(QyCrm obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectQyCrmByPrimaryKey
     * @Description: TODO(获取数据上传)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<QyCrm>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    SingleResult<QyCrmVo> selectQyCrmByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteQyCrmByQyCrmId
     * @Description: TODO(删除数据上传)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse deleteQyCrmByQyCrmId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核数据上传)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
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
     * @author: ArvinZou
     * @version: 2019-03-19
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
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception;


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map<String, Object>
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除数据上传 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    public MessageResponse deleteQyCrmByQyCrmIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-03-19
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;

    /**
     * 获取群英已介入设备列表
     *
     * @param sn 可选参数
     * @return DeviceRst
     */
    DeviceRst getDevice(String sn);

    /**
     * 调用群英接口
     * 上传员工数据&同步数据到设备
     *
     * @param userId 用户ID
     * @param idStr  设备SN串
     * @return Map
     * @throws Exception
     */
    MessageResponse syncData(String userId, String idStr);
}
