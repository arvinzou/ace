package com.huacainfo.ace.partyschool.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.StudentDao;
import com.huacainfo.ace.partyschool.vo.MailListContent;
import com.huacainfo.ace.portal.tools.TreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.MailListDao;
import com.huacainfo.ace.partyschool.model.MailList;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.MailListService;
import com.huacainfo.ace.partyschool.vo.MailListVo;
import com.huacainfo.ace.partyschool.vo.MailListQVo;

@Service("mailListService")
/**
 * @author: 陈晓克
 * @version: 2019-01-12
 * @Description: TODO(通讯录)
 */
public class MailListServiceImpl implements MailListService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailListDao mailListDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


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
    @Override
    public PageResult<MailListVo> findMailListList(MailListQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<MailListVo> rst = new PageResult<>();
        List<MailListVo> list = this.mailListDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.mailListDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMailList
     * @Description: TODO(添加通讯录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @Override
    public MessageResponse insertMailList(MailList o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "小组名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPid())) {
            return new MessageResponse(1, "所属班级不能为空！");
        }
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());


        this.mailListDao.insert(o);
        this.dataBaseLogService.log("添加通讯录", "通讯录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateMailList
     * @Description: TODO(更新通讯录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    @Override
    public MessageResponse updateMailList(MailList o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "小组名称不能为空！");
        }
        this.mailListDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更通讯录", "通讯录", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

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
    @Override
    public SingleResult<MailListVo> selectMailListByPrimaryKey(String id) throws Exception {
        SingleResult<MailListVo> rst = new SingleResult<>();
        rst.setValue(this.mailListDao.selectVoByPrimaryKey(id));
        return rst;
    }

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
    @Override
    public MessageResponse deleteMailListByMailListId(String id, UserProp userProp) throws
            Exception {
        this.mailListDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通讯录", "通讯录", id, id,
                "通讯录", userProp);
        return new MessageResponse(0, "删除成功！");
    }


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
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核通讯录", "通讯录", id, id,
                "通讯录", userProp);
        return new MessageResponse(0, "审核成功！");
    }


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

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            MailList o = new MailList();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            this.logger.info(o.toString());
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "小组名称不能为空！");
            }
            o.setId(GUIDUtil.getGUID());
            o.setCreateDate(new Date());
            o.setPid("0");
            int t = this.mailListDao.isExit(o);
            if (t > 0) {
                this.mailListDao.updateByPrimaryKey(o);

            } else {
                this.mailListDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("通讯录导入", "通讯录", "", "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


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
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.mailListDao.getList(p));

        return rst;

    }


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
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<String, Object>();
        List<Map<String, Object>> list = this.mailListDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

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
    @Override
    public MessageResponse deleteMailListByMailListIds(String[] id, UserProp userProp) throws Exception {

        this.mailListDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除通讯录", "通讯录", id[0], id[0], "通讯录", userProp);
        return new MessageResponse(0, "删除成功！");

    }


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
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception {
        this.mailListDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "通讯录", id, id, "通讯录", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * @throws
     * @Title:getTreeList
     * @Description: TODO(加载通讯录)
     * @param: @return
     * @return: List<Tree>
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    @Override
    public ListResult<Tree> getTreeList(String name, UserProp userProp) {
        List<Map<String, Object>> list = null;
        Map<String, String> o = studentDao.selectUserClassInfo(userProp.getUserId());
        if (o.get("role").equals("student")) {
            list = this.mailListDao.getClassTreeList(o.get("classId"), name);
        } else {

            list = this.mailListDao.getTeacherTreeList(name);
        }
        CommonTreeUtils treeUtils = new CommonTreeUtils(list);
        ListResult rst=new ListResult();
        rst.setValue(treeUtils.getTreeList("0"));
        return rst;
    }

    /**
     * @throws
     * @Title:getMailListContent
     * @Description: TODO(加载班级分组列表)
     * @param: @return
     * @return: List<MailListContent>
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    @Override
    public ListResult<MailListContent> getMailListContent(String classId) {
        ListResult rst=new ListResult();
        rst.setValue(this.mailListDao.getMailListContent(classId));
        return rst;
    }

    /**
     * @throws
     * @Title:updateClassesByIds
     * @Description: TODO(分组更)
     * @param: @return
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2019-01-12
     */
    @Override
    public MessageResponse updateClassesByIds(String classId, String[] ids) {
        this.mailListDao.updateClassesByIds(classId, ids);
        return new MessageResponse(0, "成功！");
    }

}
