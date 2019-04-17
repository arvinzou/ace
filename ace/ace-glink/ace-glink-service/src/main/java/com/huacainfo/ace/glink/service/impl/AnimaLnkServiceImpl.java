package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.AnimaLnkDao;
import com.huacainfo.ace.glink.model.AnimaLnk;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.AnimaLnkService;
import com.huacainfo.ace.glink.vo.AnimaLnkVo;
import com.huacainfo.ace.glink.vo.AnimaLnkQVo;

@Service("animaLnkService")
/**
 * @author: luocan
 * @version: 2019-04-10
 * @Description: TODO(节目上传)
 */
public class AnimaLnkServiceImpl implements AnimaLnkService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AnimaLnkDao animaLnkDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    @Autowired
    private SqlSessionTemplate sqlSession;
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(节目上传分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <AnimaLnkVo>
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public PageResult<AnimaLnkVo> findAnimaLnkList(AnimaLnkQVo condition,
                                          int start, int limit, String orderBy) throws Exception {
        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        AnimaLnkDao dao = session.getMapper(AnimaLnkDao.class);
        PageResult<AnimaLnkVo> rst = new PageResult<>();
        try {
            List<AnimaLnkVo> list = dao.findList(condition, start, limit, orderBy);
            rst.setRows(list);
            if (rst.getTotal()  <= 1) {
                int allRows = dao.findCount(condition);
                rst.setTotal(allRows);
            }
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAnimaLnk
     * @Description: TODO(添加节目上传)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse insertAnimaLnk(List<AnimaLnk> list, UserProp userProp) throws Exception {
        if(list.size()<1){
            return new MessageResponse(1, "请选择下发的节目！");
        }else {
            for (int i=0; i<list.size(); i++) {
                AnimaLnk o = list.get(i);
                o.setCreateDate(new Date());
                o.setStatus("1");
                o.setId(GUIDUtil.getGUID());

                if (CommonUtils.isBlank(o.getAiCode())) {
                    return new MessageResponse(1, "节目编号不能为空！");
                }
                if (CommonUtils.isBlank(o.getLnkCode())) {
                    return new MessageResponse(1, "建筑/节点/站点编码不能为空！");
                }
                int t = this.animaLnkDao.isExit(o);
                if (t > 0) {
                    return new MessageResponse(1, "节目下发重复！");

                } else {
                    this.animaLnkDao.insert(o);
                }
            }
            return new MessageResponse(0, "节目下发成功！");
        }
    }

    /**
     * @throws
     * @Title:updateAnimaLnk
     * @Description: TODO(更新节目上传)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse updateAnimaLnk(AnimaLnk o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAiCode())) {
            return new MessageResponse(1, "节目编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getLnkCode())) {
            return new MessageResponse(1, "建筑/节点/站点编码不能为空！");
        }
        this.animaLnkDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更节目上传", "节目上传", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectAnimaLnkByPrimaryKey
     * @Description: TODO(获取节目上传)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<AnimaLnk>
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public SingleResult
            <AnimaLnkVo> selectAnimaLnkByPrimaryKey(String id) throws Exception {
        SingleResult
                <AnimaLnkVo> rst = new SingleResult<>();
        rst.setValue(this.animaLnkDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAnimaLnkByAnimaLnkId
     * @Description: TODO(删除节目上传)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: luocan
     * @version: 2019-04-10
     */
    @Override
    public MessageResponse deleteAnimaLnkByAnimaLnkId(String id, UserProp userProp) throws
            Exception {
        this.animaLnkDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除节目上传", "节目上传", id, id,
                "节目上传", userProp);
        return new MessageResponse(0, "删除成功！");
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
     * @author: luocan
     * @version: 2019-04-10
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            AnimaLnk o = new AnimaLnk();
            CommonBeanUtils.copyMap2Bean(o, row);
            o.setCreateDate(new Date());
            o.setStatus("1");

            this.logger.info(o.toString());
            if (true) {
                return new MessageResponse(1, "行" + i + ",编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getId())) {
                return new MessageResponse(1, "主键不能为空！");
            }
            if (CommonUtils.isBlank(o.getAiCode())) {
                return new MessageResponse(1, "节目编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getLnkCode())) {
                return new MessageResponse(1, "建筑/节点/站点编码不能为空！");
            }

            int t = this.animaLnkDao.isExit(o);
            if (t > 0) {
                this.animaLnkDao.updateByPrimaryKey(o);

            } else {
                this.animaLnkDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("节目上传导入", "节目上传", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }

    public MessageResponse updatePrePlayUrl(String id, String prePlayUrl) throws Exception {
        animaLnkDao.updatePrePlayUrl(id, prePlayUrl);
        return new MessageResponse(0, "替换成功！");
    }
}
