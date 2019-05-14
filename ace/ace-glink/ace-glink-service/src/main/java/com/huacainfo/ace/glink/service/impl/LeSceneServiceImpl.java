package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.le.StrategysDetailOut;
import com.huacainfo.ace.glink.dao.LeSceneDao;
import com.huacainfo.ace.glink.model.LeScene;
import com.huacainfo.ace.glink.service.LeSceneService;
import com.huacainfo.ace.glink.vo.LeSceneQVo;
import com.huacainfo.ace.glink.vo.LeSceneVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("leSceneService")
/**
 * @author: Arvin
 * @version: 2019-05-13
 * @Description: TODO(弱电场景管理)
 */
public class LeSceneServiceImpl implements LeSceneService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LeSceneDao leSceneDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电场景管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <LeSceneVo>
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public PageResult<LeSceneVo> findLeSceneList(LeSceneQVo condition,
                                                 int start, int limit, String orderBy) throws Exception {
        PageResult<LeSceneVo> rst = new PageResult<>();
        List<LeSceneVo> list = leSceneDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.leSceneDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertLeScene
     * @Description: TODO(添加弱电场景管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public MessageResponse insertLeScene(LeScene o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSceneNum())) {
            return new MessageResponse(1, "场景编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态 不能为空！");
        }


        int temp = this.leSceneDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "弱电场景管理名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.leSceneDao.insert(o);
        this.dataBaseLogService.log("添加弱电场景管理", "弱电场景管理", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateLeScene
     * @Description: TODO(更新弱电场景管理)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public MessageResponse updateLeScene(LeScene o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSceneNum())) {
            return new MessageResponse(1, "场景编号不能为空！");
        }


        o.setStatus("1");
        o.setUpdateDate(DateUtil.getNowDate());
        this.leSceneDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更弱电场景管理", "弱电场景管理", "", o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:selectLeSceneByPrimaryKey
     * @Description: TODO(获取弱电场景管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeScene>
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public SingleResult
            <LeSceneVo> selectLeSceneByPrimaryKey(String id) throws Exception {
        SingleResult
                <LeSceneVo> rst = new SingleResult<>();
        rst.setValue(this.leSceneDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteLeSceneByLeSceneId
     * @Description: TODO(删除弱电场景管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public MessageResponse deleteLeSceneByLeSceneId(String id, UserProp userProp) throws
            Exception {
        this.leSceneDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除弱电场景管理", "弱电场景管理", id, id,
                "弱电场景管理", userProp);
        return new MessageResponse(0, "删除成功！");
    }


    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核弱电场景管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public MessageResponse audit(String id, String rst, String remark,
                                 UserProp userProp) throws Exception {


        dataBaseLogService.log("审核弱电场景管理", "弱电场景管理", id, id,
                "弱电场景管理", userProp);
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
     * @author: Arvin
     * @version: 2019-05-13
     */

    @Override
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
        int i = 1;
        for (Map<String, Object> row : list) {
            LeScene o = new LeScene();
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
            if (CommonUtils.isBlank(o.getSceneNum())) {
                return new MessageResponse(1, "场景编号不能为空！");
            }
            if (CommonUtils.isBlank(o.getStatus())) {
                return new MessageResponse(1, "状态 不能为空！");
            }

            int t = this.leSceneDao.isExist(o);
            if (t > 0) {
                this.leSceneDao.updateByPrimaryKey(o);

            } else {
                this.leSceneDao.insert(o);
            }
            i++;
        }
        this.dataBaseLogService.log("弱电场景管理导入", "弱电场景管理", "",
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
     * @return: ListResult<Map < String, Object>>
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public ListResult<Map<String, Object>> getList(Map<String, Object> p) throws Exception {
        ListResult<Map<String, Object>> rst = new ListResult<>();
        rst.setValue(this.leSceneDao.getList(p));

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
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public Map<String, Object> getListByCondition(Map<String, Object> params) {
        Map<String, Object> rst = new HashMap<>();
        List<Map<String, Object>> list = this.leSceneDao.getListByCondition(params);
        rst.put("total", list.size());
        rst.put("rows", list);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteRoadSectionByRoadSectionIds
     * @Description: TODO(批量删除弱电场景管理 ）
     * @param: @param ids
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public MessageResponse deleteLeSceneByLeSceneIds(String[] id, UserProp userProp)
            throws Exception {

        this.leSceneDao.deleteByPrimaryKeys(id);
        this.dataBaseLogService.log("批量删除弱电场景管理", "弱电场景管理", id[0],
                id[0], "弱电场景管理", userProp);
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
     * @author: Arvin
     * @version: 2019-05-13
     */
    @Override
    public MessageResponse updateStatus(String id, String status, UserProp userProp) throws
            Exception {
        this.leSceneDao.updateStatus(id, status);
        this.dataBaseLogService.log("跟新状态", "弱电场景管理", id, id,
                "弱电场景管理", userProp);
        return new MessageResponse(0, "成功！");
    }

    /**
     * 同步中控机数据
     *
     * @param curUserProp 当前登录用户信息
     * @return MessageResponse 处理结果
     * @throws Exception
     */
    @Override
    public MessageResponse syncData(UserProp curUserProp) {
        StrategysDetailOut out;
        try {
            //示例数据
//            String rstJson = "{\"code\":200,\"data\":[{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0301\",\"strategyNum\":\"SHGL#0003\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0302\",\"strategyNum\":\"SHGL#0004\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0303\",\"strategyNum\":\"SHGL#0005\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0308\",\"strategyNum\":\"SHGL#0006\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH04\",\"strategyNum\":\"SHGL#0007\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH10\",\"strategyNum\":\"SHGL#0008\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK01\",\"strategyNum\":\"SHGL#0009\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190226\",\"strategyNum\":\"SHGL#0010\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190220\",\"strategyNum\":\"SHGL#0011\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0227\",\"strategyNum\":\"SHGL#0012\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH02\",\"strategyNum\":\"SHGL#0013\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH03\",\"strategyNum\":\"SHGL#0014\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH05\",\"strategyNum\":\"SHGL#0015\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK03\",\"strategyNum\":\"SHGL#0018\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK04\",\"strategyNum\":\"SHGL#0019\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK05\",\"strategyNum\":\"SHGL#0020\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK06\",\"strategyNum\":\"SHGL#0021\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK07\",\"strategyNum\":\"SHGL#0022\"},{\"isPlaying\":0,\"strategyExplain\":\"周播放文件\",\"strategyName\":\"WEEK02\",\"strategyNum\":\"SHGL#0023\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0415\",\"strategyNum\":\"SHGL#0024\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0501\",\"strategyNum\":\"SHGL#0025\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH06\",\"strategyNum\":\"SHGL#0026\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH01\",\"strategyNum\":\"SHGL#0027\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH07\",\"strategyNum\":\"SHGL#0028\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH08\",\"strategyNum\":\"SHGL#0029\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH09\",\"strategyNum\":\"SHGL#0030\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190501\",\"strategyNum\":\"SHGL#0031\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY191001\",\"strategyNum\":\"SHGL#0032\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_1001\",\"strategyNum\":\"SHGL#0033\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY191201\",\"strategyNum\":\"SHGL#0034\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190601\",\"strategyNum\":\"SHGL#0035\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH12\",\"strategyNum\":\"SHGL#0036\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0601\",\"strategyNum\":\"SHGL#0037\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY191101\",\"strategyNum\":\"SHGL#0038\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190701\",\"strategyNum\":\"SHGL#0039\"},{\"isPlaying\":0,\"strategyExplain\":\"月播放文件\",\"strategyName\":\"MONTH11\",\"strategyNum\":\"SHGL#0040\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0502\",\"strategyNum\":\"SHGL#0041\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0503\",\"strategyNum\":\"SHGL#0042\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190316\",\"strategyNum\":\"SHGL#0043\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190317\",\"strategyNum\":\"SHGL#0044\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190318\",\"strategyNum\":\"SHGL#0045\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190319\",\"strategyNum\":\"SHGL#0046\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0901\",\"strategyNum\":\"SHGL#0047\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_1101\",\"strategyNum\":\"SHGL#0048\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_1201\",\"strategyNum\":\"SHGL#0049\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0701\",\"strategyNum\":\"SHGL#0050\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190502\",\"strategyNum\":\"SHGL#0051\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0508\",\"strategyNum\":\"SHGL#0052\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190508\",\"strategyNum\":\"SHGL#0053\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0509\",\"strategyNum\":\"SHGL#0054\"},{\"isPlaying\":0,\"strategyExplain\":\"事件播放文件\",\"strategyName\":\"TY190509\",\"strategyNum\":\"SHGL#0055\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_0510\",\"strategyNum\":\"SHGL#0056\"},{\"isPlaying\":0,\"strategyExplain\":\"假日播放文件\",\"strategyName\":\"HOT_1225\",\"strategyNum\":\"SHGL#0057\"}],\"message\":\"查询成功！\"}\n";
//            out = JsonUtil.toObject(rstJson, StrategysDetailOut.class);

            out = LeApiToolKit.strategysDetail();
        } catch (Exception e) {
            return new MessageResponse(ResultCode.FAIL, "接口通信异常");
        }
        if (out == null || CollectionUtils.isEmpty(out.getData())) {
            return new MessageResponse(ResultCode.FAIL, "接口数据获取异常");
        }
        //


        //
        LeScene r;
        for (StrategysDetailOut.Strategy i : out.getData()) {
            r = new LeScene();
            r.setId(GUIDUtil.getGUID());
            r.setSceneNum(i.getStrategyNum());
            r.setSceneName(i.getStrategyName());
            r.setSceneDepict(i.getStrategyExplain());
            r.setPlayStatus(i.getIsPlaying() + "");
            r.setStatus("1");
            r.setCreateDate(DateUtil.getNowDate());

            leSceneDao.insert(r);
        }


        return new MessageResponse(ResultCode.SUCCESS, "数据同步完成");
    }

}
