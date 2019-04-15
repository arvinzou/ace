package com.huacainfo.ace.glink.service.impl;


import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.glink.dao.SceneExecutorDao;
import com.huacainfo.ace.glink.model.SceneExecutor;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SceneExecutorService;
import com.huacainfo.ace.glink.vo.SceneExecutorVo;
import com.huacainfo.ace.glink.vo.SceneExecutorQVo;

@Service("sceneExecutorService")
/**
 * @author: huacai003
 * @version: 2019-04-15
 * @Description: TODO(场景执行)
 */
public class SceneExecutorServiceImpl implements SceneExecutorService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SceneExecutorDao sceneExecutorDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场景执行分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SceneExecutorVo>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public PageResult<SceneExecutorVo> findSceneExecutorList(SceneExecutorQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult
                <SceneExecutorVo> rst = new PageResult<>();
        List
                <SceneExecutorVo> list = sceneExecutorDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.sceneExecutorDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSceneExecutor
     * @Description: TODO(添加场景执行)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse insertSceneExecutor(SceneExecutor o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoopKey())) {
            return new MessageResponse(1, "回路编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getSceneCfgId())) {
            return new MessageResponse(1, "场景配置ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.sceneExecutorDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "场景执行名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        this.sceneExecutorDao.insert(o);
        this.dataBaseLogService.log("添加场景执行", "场景执行", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSceneExecutor
     * @Description: TODO(更新场景执行)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateSceneExecutor(SceneExecutor o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getLoopKey())) {
            return new MessageResponse(1, "回路编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getSceneCfgId())) {
            return new MessageResponse(1, "场景配置ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }

        this.sceneExecutorDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更场景执行", "场景执行", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSceneExecutorByPrimaryKey
     * @Description: TODO(获取场景执行)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SceneExecutor>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public SingleResult<SceneExecutorVo> selectSceneExecutorByPrimaryKey(String id) throws Exception {
        SingleResult<SceneExecutorVo> rst = new SingleResult<>();
        rst.setValue(this.sceneExecutorDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSceneExecutorBySceneExecutorId
     * @Description: TODO(删除场景执行)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteSceneExecutorBySceneExecutorId(String id, UserProp userProp) throws Exception {
        this.sceneExecutorDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除场景执行", "场景执行", id, id,
                "场景执行", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核场景执行)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {
        dataBaseLogService.log("审核场景执行", "场景执行", id, id,
                "场景执行", userProp);
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
     * @author: huacai003
     * @version: 2019-04-15
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            SceneExecutor o = new SceneExecutor();
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
            if (CommonUtils.isBlank(o.getLoopKey())) {
                return new MessageResponse(1, "回路编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getSceneCfgId())) {
                return new MessageResponse(1, "场景配置ID不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }
            int t = this.sceneExecutorDao.isExit(o);
            if (t > 0) {
                this.sceneExecutorDao.updateByPrimaryKey(o);

            } else {
                this.sceneExecutorDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("场景执行导入", "场景执行", "",
                "rs.xls", "rs.xls", userProp);
        return new MessageResponse(0, "导入成功！");
    }


    /**
     * @throws
     * @Title:getList
     * @Description: TODO(条件查询数据)
     * @param: @param p
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult
     * <Map
     * <String
     * ,Object>>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.sceneExecutorDao.getList(p));
        return rst;

    }


    /**
     * @throws
     * @Title:getListByCondition
     * @Description: TODO(用于控件数据获取)
     * @param: @param params
     * @param: @return
     * @return: Map
     * <String
     * ,Object>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.sceneExecutorDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除场景执行）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteSceneExecutorBySceneExecutorIds(String[] id, UserProp userProp)
            throws Exception {

        this.sceneExecutorDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除场景执行", "场景执行", id[0],
                id[0], "场景执行", userProp);
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
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.sceneExecutorDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "场景执行", id, id,
                "场景执行", userProp);
        return new MessageResponse(0, "成功！");
    }

}
