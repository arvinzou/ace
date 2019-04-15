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
import com.huacainfo.ace.glink.dao.SceneConfigDao;
import com.huacainfo.ace.glink.model.SceneConfig;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.glink.service.SceneConfigService;
import com.huacainfo.ace.glink.vo.SceneConfigVo;
import com.huacainfo.ace.glink.vo.SceneConfigQVo;

@Service("sceneConfigService")
/**
 * @author: huacai003
 * @version: 2019-04-15
 * @Description: TODO(场景设置)
 */
public class SceneConfigServiceImpl implements SceneConfigService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SceneConfigDao sceneConfigDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(场景设置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <SceneConfigVo>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public PageResult<SceneConfigVo> findSceneConfigList(SceneConfigQVo condition, int start, int limit, String orderBy) throws Exception {
        PageResult<SceneConfigVo> rst = new PageResult<>();
        List<SceneConfigVo> list = sceneConfigDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.sceneConfigDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSceneConfig
     * @Description: TODO(添加场景设置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse insertSceneConfig(SceneConfig o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDistrict())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getLinkCode())) {
            return new MessageResponse(1, "节点/站点编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "策略编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "策略名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.sceneConfigDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "场景设置名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.sceneConfigDao.insert(o);
        this.dataBaseLogService.log("添加场景设置", "场景设置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSceneConfig
     * @Description: TODO(更新场景设置)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse updateSceneConfig(SceneConfig o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDistrict())) {
            return new MessageResponse(1, "行政区划不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "分类不能为空！");
        }
        if (CommonUtils.isBlank(o.getLinkCode())) {
            return new MessageResponse(1, "节点/站点编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getCode())) {
            return new MessageResponse(1, "策略编码不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "策略名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }
        this.sceneConfigDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更场景设置", "场景设置", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectSceneConfigByPrimaryKey
     * @Description: TODO(获取场景设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SceneConfig>
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public SingleResult<SceneConfigVo> selectSceneConfigByPrimaryKey(String id) throws Exception {
        SingleResult
                <SceneConfigVo> rst = new SingleResult<>();
        rst.setValue(this.sceneConfigDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteSceneConfigBySceneConfigId
     * @Description: TODO(删除场景设置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteSceneConfigBySceneConfigId(String id, UserProp userProp) throws
            Exception {
        this.sceneConfigDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除场景设置", "场景设置", id, id,
                "场景设置", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核场景设置)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception {
        dataBaseLogService.log("审核场景设置", "场景设置", id, id,
                "场景设置", userProp);
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
        for (Map
                <String
                        , Object> row : list) {
            SceneConfig o = new SceneConfig();
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
            if (CommonUtils.isBlank(o.getDistrict())) {
                return new MessageResponse(1, "行政区划不能为空！");
            }
            if (CommonUtils.isBlank(o.getCategory())) {
                return new MessageResponse(1, "分类不能为空！");
            }
            if (CommonUtils.isBlank(o.getLinkCode())) {
                return new MessageResponse(1, "节点/站点编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getCode())) {
                return new MessageResponse(1, "策略编码不能为空！");
            }
            if (CommonUtils.isBlank(o.getName())) {
                return new MessageResponse(1, "策略名称不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.sceneConfigDao.isExit(o);
            if (t > 0) {
                this.sceneConfigDao.updateByPrimaryKey(o);

            } else {
                this.sceneConfigDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("场景设置导入", "场景设置", "",
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
        rst.setValue(this.sceneConfigDao.getList(p));
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
        List<Map<String, Object>> list = this.sceneConfigDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除场景设置）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: huacai003
     * @version: 2019-04-15
     */
    @Override
    public MessageResponse deleteSceneConfigBySceneConfigIds(String[] id, UserProp userProp)
            throws Exception {
        this.sceneConfigDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除场景设置", "场景设置", id[0],
                id[0], "场景设置", userProp);
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
        this.sceneConfigDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "场景设置", id, id,
                "场景设置", userProp);
        return new MessageResponse(0, "成功！");
    }

}
