package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopLawPaper;
import com.huacainfo.ace.fop.vo.FopLawPaperQVo;
import com.huacainfo.ace.fop.vo.FopLawPaperVo;

/**
 * @author: Arvin
 * @version: 2018-05-02
 * @Description: TODO(法律文书)
 */
public interface FopLawPaperService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(法律文书分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<FopLawPaperVo>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract PageResult<FopLawPaperVo> findFopLawPaperList(FopLawPaperQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertFopLawPaper
     * @Description: TODO(添加法律文书)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse insertFopLawPaper(FopLawPaper obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopLawPaper
     * @Description: TODO(更新法律文书)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse updateFopLawPaper(FopLawPaper obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopLawPaperByPrimaryKey
     * @Description: TODO(获取法律文书)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopLawPaper>
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract SingleResult<FopLawPaperVo> selectFopLawPaperByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopLawPaperByFopLawPaperId
     * @Description: TODO(删除法律文书)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-02
     */
    public abstract MessageResponse deleteFopLawPaperByFopLawPaperId(String id, UserProp userProp) throws Exception;


}
