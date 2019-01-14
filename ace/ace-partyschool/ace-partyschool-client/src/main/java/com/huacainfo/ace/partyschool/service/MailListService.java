package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.partyschool.model.MailList;
import com.huacainfo.ace.partyschool.vo.MailListContent;
import com.huacainfo.ace.partyschool.vo.MailListVo;
import com.huacainfo.ace.partyschool.vo.MailListQVo;
import com.huacainfo.ace.common.model.view.Tree;

import java.util.Map;
import java.util.List;

/**
 * @author: 陈晓克
 * @version: 2019-01-12
 * @Description: TODO(通讯录)
 */
public interface MailListService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通讯录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MailListVo>
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    PageResult<MailListVo> findMailListList(MailListQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertMailList
     * @Description: TODO(添加通讯录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    MessageResponse insertMailList(MailList obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateMailList
     * @Description: TODO(更新通讯录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    MessageResponse updateMailList(MailList obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectMailListByPrimaryKey
     * @Description: TODO(获取通讯录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MailList>
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    SingleResult<MailListVo> selectMailListByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteMailListByMailListId
     * @Description: TODO(删除通讯录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    MessageResponse deleteMailListByMailListId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核通讯录)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
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
     * @version: 2019-01-12
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
     * @version: 2019-01-12
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
     * @version: 2019-01-12
     */
    public Map<String, Object> getListByCondition(Map<String, Object> params);


    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除通讯录）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    public MessageResponse deleteMailListByMailListIds(String[] id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:getTreeList
     * @Description: TODO(加载通讯录)
     * @param: @return
     * @return: List<Tree>
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    ListResult<Tree> getTreeList(String name,UserProp userProp);

    /**
     * @throws
     * @Title:getMailListContent
     * @Description: TODO(加载班级分组列表)
     * @param: @return
     * @return: List<MailListContent>
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    ListResult<MailListContent> getMailListContent(String classId);
    /**
     * @throws
     * @Title:updateClassesByIds
     * @Description: TODO(分组更)
     * @param: @return
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    MessageResponse updateClassesByIds(String classId,String[] ids);



    /**
     * @throws
     * @Title:getClassList
     * @Description: TODO(加载当前班级列表)
     * @param: @return
     * @return: ListResult<Map<String, Object>>
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    ListResult<Map<String, Object>> getClassList(UserProp userProp);
}
