package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.society.model.Circle;
import com.huacainfo.ace.society.model.CircleImg;
import com.huacainfo.ace.society.model.Rpt;
import com.huacainfo.ace.society.vo.CircleVo;
import com.huacainfo.ace.society.vo.CircleQVo;


import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2018-09-20
 * @Description: TODO(圈子)
 */
public interface CircleService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(圈子分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CircleVo>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    PageResult
            <CircleVo> findCircleList(CircleQVo condition,
                                      int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCircle
     * @Description: TODO(添加圈子)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    MessageResponse insertCircle(Circle o, List<CircleImg> imgs) throws Exception;

    /**
     * @throws
     * @Title:updateCircle
     * @Description: TODO(更新圈子)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    MessageResponse updateCircle(Circle o, List<CircleImg> imgs) throws Exception;

    /**
     * @throws
     * @Title:selectCircleByPrimaryKey
     * @Description: TODO(获取圈子)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Circle>
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    SingleResult
            <CircleVo> selectCircleByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCircleByCircleId
     * @Description: TODO(删除圈子)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    MessageResponse deleteCircleByCircleId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核圈子)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-09-20
     */
    MessageResponse audit(String id,String rst,String text, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getList
     * @Description: TODO(圈子获取列表)
     * @param: @param start 开始行号
     * @param: @param limit 页面展示行数
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-10-15
     */
    List<Rpt> getList(int start,int limit)  throws Exception;
}
