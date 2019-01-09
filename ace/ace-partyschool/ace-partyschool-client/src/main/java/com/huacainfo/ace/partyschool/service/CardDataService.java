package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.CardData;
import com.huacainfo.ace.partyschool.vo.CardDataQVo;
import com.huacainfo.ace.partyschool.vo.CardDataVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-01-08
 * @Description: TODO(一卡通绑定)
 */
public interface CardDataService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(一卡通绑定分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CardDataVo>
     * @author: Arvin
     * @version: 2019-01-08
     */
    PageResult<CardDataVo> findCardDataList(CardDataQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCardData
     * @Description: TODO(添加一卡通绑定)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    MessageResponse insertCardData(CardData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCardData
     * @Description: TODO(更新一卡通绑定)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    MessageResponse updateCardData(CardData obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCardDataByPrimaryKey
     * @Description: TODO(获取一卡通绑定)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CardData>
     * @author: Arvin
     * @version: 2019-01-08
     */
    SingleResult<CardDataVo> selectCardDataByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCardDataByCardDataId
     * @Description: TODO(删除一卡通绑定)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    MessageResponse deleteCardDataByCardDataId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核一卡通绑定)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
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
     * @version: 2019-01-08
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
     * @version: 2019-01-08
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
     * @version: 2019-01-08
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除一卡通绑定
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-08
     */
    MessageResponse deleteCardDataByCardDatas(String[] id, UserProp userProp) throws Exception;

    /**
     * 加载系统所有用户列表，切条件模糊查询
     *
     * @param params 参数
     * @return MessageResponse
     * @throws Exception
     */
    Map<String, Object> findUserList(Map<String, Object> params);

}
