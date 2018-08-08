package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.PostLevelDao;
import com.huacainfo.ace.jxb.model.PostLevel;
import com.huacainfo.ace.jxb.service.PostLevelService;
import com.huacainfo.ace.jxb.vo.PostLevelQVo;
import com.huacainfo.ace.jxb.vo.PostLevelVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("postLevelService")
/**
 * @author: Arvin
 * @version: 2018-08-08
 * @Description: TODO(咨询师岗位级别配置)
 */
public class PostLevelServiceImpl implements PostLevelService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PostLevelDao postLevelDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(咨询师岗位级别配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <PostLevelVo>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public PageResult<PostLevelVo> findPostLevelList(PostLevelQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<PostLevelVo> rst = new PageResult<>();
        List<PostLevelVo> list = this.postLevelDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.postLevelDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertPostLevel
     * @Description: TODO(添加咨询师岗位级别配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public MessageResponse insertPostLevel(PostLevel o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getPostName())) {
            return new MessageResponse(1, "岗位名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getRatio())) {
            return new MessageResponse(1, "分配比例(分给老师的比例demo:0.5)不能为空！");
        }

        o.setPostIndex("1");
        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.postLevelDao.insertSelective(o);
        this.dataBaseLogService.log("添加咨询师岗位级别配置", "咨询师岗位级别配置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加咨询师岗位级别配置完成！");
    }

    /**
     * @throws
     * @Title:updatePostLevel
     * @Description: TODO(更新咨询师岗位级别配置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public MessageResponse updatePostLevel(PostLevel o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getPostName())) {
            return new MessageResponse(1, "岗位名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getPostIndex())) {
            return new MessageResponse(1, "岗位序号不能为空！");
        }
        if (CommonUtils.isBlank(o.getRatio())) {
            return new MessageResponse(1, "分配比例 (分给老师的比例demo:0.5)不能为空！");
        }

        this.postLevelDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更咨询师岗位级别配置", "咨询师岗位级别配置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更咨询师岗位级别配置完成！");
    }

    /**
     * @throws
     * @Title:selectPostLevelByPrimaryKey
     * @Description: TODO(获取咨询师岗位级别配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<PostLevel>
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public SingleResult<PostLevelVo> selectPostLevelByPrimaryKey(String id) throws Exception {
        SingleResult<PostLevelVo> rst = new SingleResult<>();
        rst.setValue(this.postLevelDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deletePostLevelByPostLevelId
     * @Description: TODO(删除咨询师岗位级别配置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-08
     */
    @Override
    public MessageResponse deletePostLevelByPostLevelId(String id, UserProp userProp) throws
            Exception {
        this.postLevelDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除咨询师岗位级别配置", "咨询师岗位级别配置",
                String.valueOf(id), String.valueOf(id), "咨询师岗位级别配置", userProp);
        return new MessageResponse(0, "咨询师岗位级别配置删除完成！");
    }

}
