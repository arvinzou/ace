package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.CorporateStyle;
import com.huacainfo.ace.fop.vo.CorporateStyleVo;
import com.huacainfo.ace.fop.vo.CorporateStyleQVo;

/**
 * @author: huacai003
 * @version: 2018-05-17
 * @Description: TODO(企业风采)
 */
public interface CorporateStyleService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(企业风采分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CorporateStyleVo>
     * @author: huacai003
     * @version: 2018-05-17
     */
    PageResult
            <CorporateStyleVo> findCorporateStyleList(CorporateStyleQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertCorporateStyle
     * @Description: TODO(添加企业风采)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse insertCorporateStyle(CorporateStyle obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCorporateStyle
     * @Description: TODO(更新企业风采)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse updateCorporateStyle(CorporateStyle obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCorporateStyleByPrimaryKey
     * @Description: TODO(获取企业风采)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CorporateStyle>
     * @author: huacai003
     * @version: 2018-05-17
     */
    SingleResult
            <CorporateStyleVo> selectCorporateStyleByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCorporateStyleByCorporateStyleId
     * @Description: TODO(删除企业风采)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2018-05-17
     */
    MessageResponse deleteCorporateStyleByCorporateStyleId(String id, UserProp userProp) throws Exception;

}
